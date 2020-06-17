package com.bzm.jarjartoolplugin

import com.bzm.jarjartoolplugin.bean.JarInfo
import com.bzm.jarjartoolplugin.bean.Jarjar
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecSpec

class JarjarTask extends DefaultTask{

    Jarjar jarJar

    JarjarTask() {
        group='bzm'
    }

    @TaskAction
    def run(){
        if (jarJar == null){
            throw RuntimeException("请配置Jarjar扩展")
        }

        jarJar.jarInfos.eachWithIndex { JarInfo entry, int index ->
            println "info:$entry"
            String[] rules = entry.rule.split("\\|")
            println "rules $rules"
            File file = new File(jarJar.filePath + File.separator + entry.name + ".txt")
            file.withWriter { writer ->
                rules.each {
                    println("rule write: $it")
                    writer.writeLine(it)
                }
            }
            println "File $file.text"
            project.exec { ExecSpec execSpec ->
                execSpec.commandLine("java","-jar",jarJar.jarjarPath,"process",
                        file.getAbsolutePath(),entry.jarPath,"${jarJar.filePath}${File.separator}${entry.name}.jar")
            }
            if (file.exists()){
                file.delete()
            }
        }

    }

}
