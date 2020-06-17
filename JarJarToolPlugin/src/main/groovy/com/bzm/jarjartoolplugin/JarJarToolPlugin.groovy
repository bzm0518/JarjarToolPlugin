package com.bzm.jarjartoolplugin

import com.bzm.jarjartoolplugin.bean.Jarjar
import org.gradle.api.Plugin
import org.gradle.api.Project

class JarJarToolPlugin implements Plugin<Project>{


    @Override
    void apply(Project project) {
        def jar = project.extensions.create("Jarjar",Jarjar,project) as Jarjar

        project.afterEvaluate {
            JarjarTask task = project.tasks.create("jarjarTool",JarjarTask)
            task.jarJar = jar
        }

    }
}
