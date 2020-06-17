package com.bzm.jarjartoolplugin.bean

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

class Jarjar{

    String jarjarPath

    String filePath

    NamedDomainObjectContainer<JarInfo> jarInfos

    public Jarjar(Project project){
        NamedDomainObjectContainer<JarInfo> ndoc = project.container(JarInfo)
        jarInfos = ndoc
    }

    void jarInfos(Action<NamedDomainObjectContainer<JarInfo>> action){
        action.execute(jarInfos)
    }

    void jarjarPath(String jarjarPath){
        this.jarjarPath = jarjarPath
    }

    void filePath(String filePath){
        this.filePath = filePath
    }
}

class JarInfo{
    String jarPath
    String rule
    String name

    JarInfo(String name){
        this.name = name
    }

    void jarPath(String jarPath){
        this.jarPath = jarPath
    }

    void rule(String rule){
        this.rule = rule
    }

}