package com.sandrew.springLearning.domain;

import java.util.Optional;
import java.util.Set;

public interface Aliasable<E> {

    public Set<E> getAliases();

    public Optional<E> getMaster();

    public void addAliases(Set<E> aliases);

    public void setMaster(E master);

    public void removeAlias(E alias);
}
