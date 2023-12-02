import sys
import os

ROOT = "./create-new-day"
TEMPLATES = f"{ROOT}/templates"
SRC = "./src/main/kotlin"
TEST = "./src/test/kotlin"
RESOURCES = "./src/main/resources"


def read_template(template_name):
    with open(f"./{TEMPLATES}/{template_name}.txt", "r") as template:
        return template.read()


def read_file(file_name):
    with open(file_name, "r") as file:
        return file.read()


def write_file(file_name, content):
    print(file_name)
    with open(file_name, "w") as file:
        file.write(content)


def create_new_day(day_number):
    # read the template file
    day_nn = read_template("DayNN")

    day_name = f"Day{day_number:02d}"
    write_file(f"{SRC}/{day_name}.kt", day_nn.replace("{{Day}}", str(day_number)))

    day_test_nn = read_template("DayNNTest")
    write_file(f"{TEST}/{day_name}Test.kt", day_test_nn.replace("{{Day00}}", day_name))

    advent_of_code_file_name = f"{SRC}/AdventOfCode.kt"
    advent_of_code_file = read_file(advent_of_code_file_name)
    new_day_code = f"{day_number} to {day_name}()"
    if new_day_code not in advent_of_code_file:
        new_day_line = advent_of_code_file.find("{{ NextDay }}")
        new_day_line_before_end = advent_of_code_file.rfind("\n", 0, new_day_line) + 1
        new_day_code = f"{day_number} to {day_name}()"
        advent_of_code_file = (advent_of_code_file[:new_day_line_before_end] +
                               f"{new_day_code},\n" +
                               advent_of_code_file[new_day_line_before_end:])
        write_file(advent_of_code_file_name, advent_of_code_file)

    resources_folder = f"{RESOURCES}/{day_name}"
    if not os.path.exists(resources_folder):
        os.makedirs(resources_folder)

    write_file(f"{resources_folder}/input.txt", "")
    write_file(f"{resources_folder}/sample1.txt", "")
    write_file(f"{resources_folder}/sample2.txt", "")

if __name__ == "__main__":
    if len(sys.argv) == 2:
        create_new_day(int(sys.argv[1]))
    else:
        print("Usage: python new-day.py <day-number>")
