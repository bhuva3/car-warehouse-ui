package com.sample.cwi;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class Components {

    protected final MutablePicoContainer picoContainer = new DefaultPicoContainer(new Caching());

    public <C> C get(Class<C> klass) {
        return picoContainer.getComponent(klass);
    }

    public Object get(Object key) {
        return picoContainer.getComponent(key);
    }

    public void set(Object key, Object value) {
        picoContainer.addComponent(key, value);
    }

    public <C> void set(Class<C> klass) {
        picoContainer.addComponent(klass);
    }

}
