# PF4J Gradle Demo

This demo assumes that you know the basics of Gradle (Please look at [gradle](https://gradle.org/) for more info)

### Setup/Build

1. Clone the repo
2. Go to demo_gradle `cd demo_gradle` 
3. run `./gradlew build`

* This will produce one jar, named app-plugin-demo-uberjar.jar, located in the `app/build/libs/` directory and three plugins zips located in `build/plugins` directory.
* The plugins are `plugin-hello-plugin-0.0.1.zip`,  `plugin-KotlinPlugin-1.0.0.zip` and `plugin-welcome-plugin-0.0.1.zip`

### Run the demo

1. Run 

```
 ./gradlew app:run
```

2. The demo's output should look similar to: (Please see `Boot#main()` for more details)
```
[main] INFO Boot - ########################################
[main] INFO Boot -                PF4J-DEMO
[main] INFO Boot - ########################################
[main] INFO org.pf4j.DefaultPluginStatusProvider - Enabled plugins: []
[main] INFO org.pf4j.DefaultPluginStatusProvider - Disabled plugins: []
[main] INFO org.pf4j.DefaultPluginManager - PF4J version 3.1.0 in 'deployment' mode
[main] INFO org.pf4j.util.FileUtils - Expanded plugin zip 'plugin-hello-plugin-0.0.1.zip' in 'plugin-hello-plugin-0.0.1'
[main] INFO org.pf4j.util.FileUtils - Expanded plugin zip 'plugin-KotlinPlugin-1.0.0.zip' in 'plugin-KotlinPlugin-1.0.0'
[main] INFO org.pf4j.util.FileUtils - Expanded plugin zip 'plugin-welcome-plugin-0.0.1.zip' in 'plugin-welcome-plugin-0.0.1'
[main] INFO org.pf4j.AbstractPluginManager - Plugin 'welcome-plugin@0.0.1' resolved
[main] INFO org.pf4j.AbstractPluginManager - Plugin 'KotlinPlugin@1.0.0' resolved
[main] INFO org.pf4j.AbstractPluginManager - Plugin 'hello-plugin@0.0.1' resolved
[main] INFO org.pf4j.AbstractPluginManager - Start plugin 'welcome-plugin@0.0.1'
[main] INFO WelcomePlugin - WelcomePlugin.start()
[main] INFO WelcomePlugin - WELCOMEPLUGIN
[main] INFO org.pf4j.AbstractPluginManager - Start plugin 'KotlinPlugin@1.0.0'
[main] INFO org.pf4j.demo.kotlin.KotlinPlugin - KotlinPlugin.start()
[main] INFO org.pf4j.demo.kotlin.KotlinPlugin - KOTLINPLUGIN
[main] INFO org.pf4j.AbstractPluginManager - Start plugin 'hello-plugin@0.0.1'
[main] INFO HelloPlugin - HelloPlugin.start()
[main] INFO Boot - Plugindirectory:
[main] INFO Boot -        ../build/plugins

[main] INFO Boot - Found 4 extensions for extension point 'Greeting'
[main] INFO Boot - >>> Whazzup
[main] INFO Boot - >>> Welcome
[main] INFO Boot - >>> KotlinGreetings
[main] INFO Boot - >>> Hello
[main] INFO Boot - Extensions added by plugin 'welcome-plugin':
[main] INFO Boot - Extensions added by plugin 'KotlinPlugin':
[main] INFO Boot - Extensions added by plugin 'hello-plugin':
[main] INFO org.pf4j.AbstractPluginManager - Stop plugin 'hello-plugin@0.0.1'
[main] INFO HelloPlugin - HelloPlugin.stop()
[main] INFO org.pf4j.AbstractPluginManager - Stop plugin 'KotlinPlugin@1.0.0'
[main] INFO org.pf4j.demo.kotlin.KotlinPlugin - KotlinPlugin.stop()
[main] INFO org.pf4j.AbstractPluginManager - Stop plugin 'welcome-plugin@0.0.1'
[main] INFO WelcomePlugin - WelcomePlugin.stop()
```

