package webit.script;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteInitMethod;
import jodd.props.Props;
import jodd.props.PropsUtil;
import webit.script.core.text.TextStatmentFactory;
import webit.script.exceptions.ResourceNotFoundException;
import webit.script.loaders.Loader;
import webit.script.resolvers.Resolver;
import webit.script.resolvers.ResolverManager;

/**
 *
 * @author Zqq
 */
public class Engine {

    public static final String ENGINE = "engine";
    public static final String PETITE = "petite";
    //
    private static final String DEFAULT_PROPERTIES = "/webitl-default.props";
    //settings
    protected Class resourceLoaderClass;
    protected Class textStatmentFactoryClass;
    protected Class[] resolvers;
    protected String encoding;
    //
    protected TextStatmentFactory textStatmentFactory;
    protected final ResolverManager resolverManager;
    protected Loader resourceLoader;
    protected final ConcurrentMap<String, Template> templateCache;
    protected final PetiteContainer _petite;

    public Engine(PetiteContainer petite) {
        this._petite = petite;
        this.templateCache = new ConcurrentHashMap<String, Template>();
        this.resolverManager = new ResolverManager();
    }

    @PetiteInitMethod
    public void init() throws Exception {

        this.resourceLoader = (Loader) resolveNewInstance(this.resourceLoaderClass);
        this.textStatmentFactory = (TextStatmentFactory) resolveNewInstance(this.textStatmentFactoryClass);
        if (this.resolvers != null) {
            Resolver[] resolverInstances = new Resolver[this.resolvers.length];
            for (int i = 0; i < this.resolvers.length; i++) {
                resolverInstances[i] = (Resolver) resolveNewInstance(this.resolvers[i]);
            }
            this.resolverManager.init(resolverInstances);
        }
    }

    public <E> E resolveNewInstance(Class<E> type) throws InstantiationException, IllegalAccessException {

        String beanName = _petite.resolveBeanName(type);
        Object object = _petite.getBean(beanName);
        if (object == null) {
            object = type.newInstance();
            _petite.addBean(beanName, object);
        }
        if (object instanceof Configurable) {
            ((Configurable) object).init(this);
        }
        return (E) object;
    }

    public Template getTemplate(String parentName, String name) throws ResourceNotFoundException {
        return getTemplate(resourceLoader.concat(parentName, name));
    }

    public Template getTemplate(String name) throws ResourceNotFoundException {
        String normalizedName = resourceLoader.normalize(name);
        if (normalizedName == null) {
            throw new ResourceNotFoundException("TODO: 不合法的模板名:" + name);
        }
        Template template = templateCache.get(normalizedName);
        if (template == null) {
            template = new Template(this, normalizedName, resourceLoader.get(normalizedName)); //fast
            Template oldTemplate = templateCache.putIfAbsent(normalizedName, template);
            if (oldTemplate != null) {
                template = oldTemplate;
            }
        }
        return template;
    }

    public void setResourceLoaderClass(Class resourceLoaderClass) {
        this.resourceLoaderClass = resourceLoaderClass;
    }

    public void setTextStatmentFactoryClass(Class textStatmentFactoryClass) {
        this.textStatmentFactoryClass = textStatmentFactoryClass;
    }

    public void setResourceLoader(Loader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    //
    public ResolverManager getResolverManager() {
        return resolverManager;
    }

    public TextStatmentFactory getTextStatmentFactory() {
        return textStatmentFactory;
    }

    public static Engine getEngine(String configPath) {
        return getEngine(configPath, null);
    }

    public static Engine getEngine(String configPath, Map parameters) {

        PetiteContainer petite = new PetiteContainer();
        petite.getConfig().setUseFullTypeNames(true);

        Props props;
        //props.loadSystemProperties("sys");
        //props.loadEnvironment("env");

        //TODO: 记录加载的配置文件
        if (configPath != null) {
            props = PropsUtil.createFromClasspath(DEFAULT_PROPERTIES, configPath);
        } else {
            props = PropsUtil.createFromClasspath(DEFAULT_PROPERTIES);
        }

        petite.defineParameters(props);
        if (parameters != null) {
            petite.defineParameters(parameters);
        }

        petite.addBean(PETITE, petite);

        Engine engine = new Engine(petite);

        String engineBeanName = petite.resolveBeanName(Engine.class);
        petite.addBean(engineBeanName, engine);
        petite.addBean(ENGINE, engine);

        return engine;
    }
}
