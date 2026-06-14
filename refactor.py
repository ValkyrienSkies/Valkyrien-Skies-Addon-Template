import os
import sys

script_dir = os.path.dirname(__file__)

print("This script may be out of date with the template. It is recommended to commit to git before running this script, in case a roll-back is required.")
answer = input("Do you want to continue? y/n: ")
if answer.lower() == "n":
    sys.exit()


new_mod_id = input("Enter new mod id: ")
new_mod_name = input("Enter new mod name: ")
new_package = input("Enter new package: (e.g. com.myusername.mymod) ")


def refactorFile(path, conversions, relative=True):
    if (relative):
        path = os.path.join(script_dir, path)

    with open(path, "r") as fileR:
        contents = fileR.read()

    for pair in conversions:
        contents = contents.replace(pair[0], pair[1])

    with open(path, "w") as fileW:
        fileW.write(contents)

def refactorFolder(old_path, new_path):
    old_path = os.path.join(script_dir, old_path)
    new_path = os.path.join(script_dir, new_path)
    os.makedirs(new_path, exist_ok=True)
    os.rename(old_path, new_path)


# Forge - Java
refactorFile("forge/src/main/java/org/valkyrienskies/vs_template/mixin/MixinTitleScreen.java",[
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("forge/src/main/java/org/valkyrienskies/vs_template", f"forge/src/main/java/{new_package.replace(".", "/")}")

# Forge - Kotlin
refactorFile("forge/src/main/kotlin/org/valkyrienskies/vs_template/platform/ForgePlatformHelper.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("forge/src/main/kotlin/org/valkyrienskies/vs_template/VSTemplateModForge.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("forge/src/main/kotlin/org/valkyrienskies/vs_template", f"forge/src/main/kotlin/{new_package.replace(".", "/")}")

# Forge - Resources
refactorFile("forge/src/main/resources/vs_template.forge.mixins.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])

os.rename(os.path.join(script_dir, "forge/src/main/resources/vs_template.forge.mixins.json"), os.path.join(script_dir, f"forge/src/main/resources/{new_mod_id}.forge.mixins.json"))


# Fabric - Java
refactorFile("fabric/src/main/java/org/valkyrienskies/vs_template/mixin/MixinTitleScreen.java",[
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("fabric/src/main/java/org/valkyrienskies/vs_template", f"fabric/src/main/java/{new_package.replace(".", "/")}")

# Fabric - Kotlin
refactorFile("fabric/src/main/kotlin/org/valkyrienskies/vs_template/platform/FabricPlatformHelper.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("fabric/src/main/kotlin/org/valkyrienskies/vs_template/VSTemplateModFabric.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("fabric/src/main/kotlin/org/valkyrienskies/vs_template", f"fabric/src/main/kotlin/{new_package.replace(".", "/")}")

# Fabric - Resources
refactorFile("fabric/src/main/resources/vs_template.fabric.mixins.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("fabric/src/main/resources/fabric.mod.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
    ["vs_template", new_mod_id]
])
os.rename(os.path.join(script_dir, "fabric/src/main/resources/vs_template.fabric.mixins.json"), os.path.join(script_dir, f"fabric/src/main/resources/{new_mod_id}.fabric.mixins.json"))


# Common - Java
refactorFile("common/src/main/java/org/valkyrienskies/vs_template/mixin/MixinMinecraft.java",[
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("common/src/main/java/org/valkyrienskies/vs_template", f"common/src/main/java/{new_package.replace(".", "/")}")

# Common - Kotlin
refactorFile("common/src/main/kotlin/org/valkyrienskies/vs_template/platform/services/ServiceHelper.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("common/src/main/kotlin/org/valkyrienskies/vs_template/platform/PlatformHelper.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("common/src/main/kotlin/org/valkyrienskies/vs_template/VSTemplateMod.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
    ["MOD_ID = \"vs_template\"", f"MOD_ID = \"{new_mod_id}\""],
    ["MOD_NAME = \"vs_template\"", f"MOD_NAME = \"{new_mod_name}\""]
])
refactorFolder("common/src/main/kotlin/org/valkyrienskies/vs_template", f"common/src/main/kotlin/{new_package.replace(".", "/")}")

# Common - Resources
refactorFile("common/src/main/resources/vs_template.mixins.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
os.rename(os.path.join(script_dir, "common/src/main/resources/vs_template.mixins.json"), os.path.join(script_dir, f"common/src/main/resources/{new_mod_id}.mixins.json"))





# Root
refactorFile("gradle.properties", [
    ["group=org.valkyrienskies.vs_template", f"group={new_package}"],
    ["mod_name=VS Template", f"mod_name={new_mod_name}"],
    ["mod_id=vs_template", f"mod_id={new_mod_id}"]
])

refactorFile("settings.gradle", [
    ["rootProject.name = 'VS Template'", f"rootProject.name = '{new_mod_name}'"]
])
