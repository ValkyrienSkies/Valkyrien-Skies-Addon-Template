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


# Java
refactorFolder("src/main/java/org/valkyrienskies/vs_template", f"src/main/java/{new_package.replace(".", "/")}")

# Kotlin
refactorFile("src/main/kotlin/org/valkyrienskies/vs_template/client/VSTemplateModClient.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("src/main/kotlin/org/valkyrienskies/vs_template/VSTemplateMod.kt", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFolder("src/main/kotlin/org/valkyrienskies/vs_template", f"src/main/kotlin/{new_package.replace(".", "/")}")

# Resources
refactorFile("src/main/resources/vs_template.mixins.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
])
refactorFile("src/main/resources/fabric.mod.json", [
    ["org.valkyrienskies.vs_template", f"{new_package}"],
    ["vs_template", new_mod_id]
])

# Gradle
refactorFile("gradle.properties", [
    ["group=org.valkyrienskies.vs_template", f"group={new_package}"],
    ["mod_name=VS Template", f"mod_name={new_mod_name}"]
])