<p align="center">
<img src="icon_big.png" width="512" height="512">
</p>
<h1 align="center">
Valkyrien Skies : Addon Template
</h1>
<p align="center">
The official VS Addon template, for VS <b>2.4.0</b> and onward!
</p>

## Getting Started

### Namespace / Mod ID
This template's default namespace/mod ID is "vs_template"- you'll likely want to change this!

You can do so by altering the following files:
- Change the base package name in `settings.gradle`
- Change the `group`, `mod_name`, `mod_author`, and `mod_id` properties in the `gradle.properties` f
- Change the MOD_ID in `VSTemplateMod.java` in the `common` module
- Edit the `fabric.mod.json` file in `src/main/resources/` in the `fabric` module

You should also rename anything prefixed with "VSTemplate" to your mod's name!

If you are using IntelliJ, it is recommended to refactor in your IDE rather than
changing the file name in file explorer, as it will automatically edit
the references to the old name to match your new one.

Tip: 
In IntelliJ, ctrl + shift + r can be used for a project-wide find and replace. 
Triple shift can be also used for a project wide search. These are often useful when
refactoring vs_template to your own mod name.

### Understanding Dependencies
In this mod's `gradle.properties` file, you will find a few properties that define the versions of
each dependency this project has, including VS2 and its core. You  can change these properties to update
dependency versions easily across the entire project, without having to edit buildscripts!

Most dependencies have some easy way to discover the latest/reccomended version to depend on.
Valkyrien Skies itself is similar! The `vs_core_version` property should be equal to the identically named property in the [Valkyrien Skies Github Repo](https://github.com/ValkyrienSkies/Valkyrien-Skies-2),
and the `vs2_version` is the mod version, followed by the <b>first 10 characters</b> of the latest commit hash.

You can find the most recent
retrievable version of VS easily  by going to the Packages tab for [Fabric](https://github.com/ValkyrienSkies/Valkyrien-Skies-2/packages/2020982) or [Forge](https://github.com/ValkyrienSkies/Valkyrien-Skies-2/packages/2020984)!

### Building and Running
Before running the mod after each change made, it is recommended to run the `./gradlew clean` task in your command line, followed by clicking the sync button in the top right.
This ensures the files built are up to date with your code changes.

Alternatively to gradle clean, you can also manually delete your build folders. This is helpful if that task fails, for whatever reason.

### Mod Structure
This mod template uses a multiloader structure, with 4 modules:
- `buildSrc`: This module contains code which manages the other 3 modules. It shouldn't be touched, unless you wish to add project-wide repositories/dependencies.
- `common` : This module contains code shared between both Fabric and Forge. This is where the majority of your mod's code should go!
- `fabric` : This module contains code specific to the Fabric loader. This includes the Fabric mod initializer, and any Fabric-specific implementations of common code.
- `forge` : This module contains code specific to the Forge loader. This includes the Forge mod class, and any Forge-specific implementations of common code.

Within each module, you will find 2 primary submodules:
- `src/main/java` : This is where Java code goes. This is primarily used for Mixins, as Mixins cannot be written in Kotlin. You may also choose to relocate your primary mod files here, and not use Kotlin at all if you wish.
- `src/main/resources` : This is where resources go, such as Forge's `mods.toml`, Fabric's `fabric.mod.json`, mod assets, and data files.

Feel free to remove the example `MixinTitleScreen`, `MixinMinecraft`, or even the `PlatformHelper`. However, these may serve as useful examples for later code.

### Not Arch Loom

This template is not using architectury loom. This means common multiloader utilities, such as `@ExpectPlatform`, are not available.

Instead, this template comes with google autoservice added during compile time.
This allows you to use raw services to come close to `@ExpectPlatform` behaviour.

Usage:
- Create an interface class in the `common` module
- Add your method stubs you want to abstract between loaders to that interface
- Add an `INSTANCE` property to the interface like so: `MyInterface INSTANCE = ServiceHelper.load(MyInterface.class);`
- Add a class that implements the interface in both the `forge` and `fabric` module (it doesn't matter where you put the class, unlike with `@ExpectPlatform`)
- Make that class annotated with `@AutoService(MyInterface.class)`

Done! You now have a class split across loaders. 
You use its methods by doing `MyInterface.INSTANCE.myMethod();`


### Using the VS Api
You can access the VS Core Api statically through `ValkyrienSkies.api` (with parenthesis for a method call in Java). The API's javadocs contain tons of extra information on how to use each part of it, so give them a read!

Additionally, there are a few features in VS2 itself- such as `BlockEntityPhysicsListener`- that you may want to use.

<i>This template was built for Valkyrien Skies 2.4.0+. Changes in API from 2.3 will make it almost certainly not function with releases of the mod prior to 2.4, so make sure you intend to develop for that version!</i>
