# converter
## Test task converter
This program implements a unit conversion test task.

### Structure:
#### In the *model* directory:
- *Unit* - this object model of units;
- *UnitsContainers* - container for Unit;
- *InputObject* - transformed input data object, for exchanging valid data between classes;
#### In the main directory: 
- *Converter* - the main class with basic logic for conversion. An object of this class receives a *UnitsContainers* object in the constructor, with which it interacts during conversion;
- *InputHandler* - processes a string query, verifies its validity, and returns data from the query as an *InputObject* object;
- *MainConvertor* - demonstrates the algorithm of interaction with the program, organizes contact with the user.

### How it works:
- By taking data from the input stream in the format: *"1 kg = 1000 g"*, it creates a **object** unit of measure,
for example *"kg"*, and writes in it a mathematical **ratio**, in this case to *"g"*, and vice versa.
Thus, the program **UnitsContainer** will contain a **list of units**, 
and each of these **units** will contain a **list** of mathematical **ratio** with the other **units** 
with which the link has been established by the user.
- When received in from the input stream, the data in the format: *"2 kg = ? g"*, the program generates a conversion request.
  - If the **unit object** *"kg"* contains information about its mathematical **ratio** to *"g"*, it simply performs the calculation and outputs the result.
  - If the **object of unit** *"kg"* **does NOT** contain information about its mathematical **ratio** to *"g"*,
then **coincidence** is searched among already written **ratio** to both objects, if there is such **coincidence**,
then the calculation is done through that **coincidence unit**. Otherwise, the program will say that the conversion is impossible.

#

В этой программе реализовано тестовое задание на конвертацию единиц измерения.

### Структура:
#### В директории *model*:
- *Unit* - модель объекта единицы измерения;
- *UnitsContainers* - модель контейнера для объектов единиц измерения;
- *InputObject* - преобразованный объект входных данных для обмена валидными данными между классами;
#### В главной директории:
- *Converter* - основной класс с базовой логикой конвертирования. Объект этого класса получает в конструктор объект *Unity Container*, с которым он взаимодействует во время конвертирования;
- *InputHandler* - обрабатывает строковый запрос, проверяет его валидность и возвращает данные из запроса в виде объекта *InputObject*;
- *MainConvertor* - демонстрирует алгоритм взаимодействия с программой, организует контакт с пользователем.

### Как она работает:
- Принимая из входного потока данные в формате: *"1 kg = 1000 g"*, она создаёт **объект** единицы измерения,
например *"kg"*, и записывает в неё математическое **отношение**, в данном случае к *"g"*, и наоборот.
Таким образом, в **UnitsContainer** программы будет содержаться **список единиц измерения**, 
и в каждой из этих **единиц измерения** будет содержаться **список** математических **отношений** с другими **единицами измерений**, 
с которыми связь была установлена пользователем.
- При получении из входного потока данных в формате: *"2 kg = ? g"*, программа формирует запрос на конвертацию.
  - Если в **объекте единицы измерения** *"kg"* содержится информация о её математическом **отношении** к *"g"*, то просто производится расчёт и выводится результат.
  - Если в **объекте единицы измерения** *"kg"* **НЕ** содержится информация о её математическом **отношении** к *"g"*,
тогда производится поиск **совпадений** среди уже записанных **отношений** в оба объекта, если такое **совпадение** есть,
то расчёт производится через эту **совпадающую единицу измерения**. 
  - В ином случае - программа скажет, что преобразование невозможно.
