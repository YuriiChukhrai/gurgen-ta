# gurgen-ta


##ENG<br>

Software Description Gurgen1.0 The application should work on Linux 64 (glibc 2.17). At the request of the client, the application generates a roll of standard six-sided dice (from one to five dice). The client enters data on the number of rolls, the minimum and a maximum number of dice as arguments when starting the program. The application displays in the console the fallen faces on the dice and counts the result of the throw. When calculating the order of the cubes is not taken into account. Significant edges: 1 and 5. 1 - corresponds to 10 points, 5 - corresponds to 5 points. All other faces do not bring points (2,3,4,6). Exception - special combination: 1,2,3,4,5 = 150 points

Examples of running the program:

$ ./gurgen_0 65 1 4 The program will perform 65 shots. In each roll, there will be from one to four dice $ ./gurgen_0 12 3 3 The program will perform twelve rolls. Each roll will have three dice.

Examples of calculating points:

1,1,5 = 25 2,4,6,6 = 0 5,1,3,5 = 20

The task of the tester:
Identify the maximum possible number of errors in different software versions.
Automate the testing process (the final automation test will be performed on a version that is not in the attached files: gurgen_7)
Version gurgen_0 - can be considered a reference, other versions may contain errors.
    

##RUS<br>

Описание ПО Гурген1.0
Приложение должно работать на ОС Linux 64 (glibc 2.17). По запросу клиента приложение генерирует бросок стандартных шестигранных кубиков (от одного до пяти кубиков). Данные о количестве бросков, минимальном и максимальном количестве кубиков клиент вводит в качестве аргументов при запуске программы. Приложение выводит в консоль выпавшие грани на кубиках и подсчитывает результат броска. При подсчете порядок следования кубиков не учитывается. Значащие грани: 1 и 5. 1 - соответствует 10 очкам, 5 - соответствует 5 очкам. Все остальные грани не приносят очков (2,3,4,6).
Исключение - специальная комбинация: 1,2,3,4,5 = 150 очков

Примеры запуска программы:

$ ./gurgen_0 65 1 4
Программа выполнит 65 бросков. В каждом броске будет от одного до четырех кубиков
$ ./gurgen_0 12 3 3
Программа выполнит двенадцать бросков. В каждом броске будет три кубика.

Примеры вычисления набранных очков:

1,1,5 = 25 
2,4,6,6 = 0 
5,1,3,5 = 20 

Задача тестировщика:
1) Выявить максимально возможное количество ошибок в различных версиях ПО
2) Автоматизировать процесс тестирования (финальная проверка автоматизации будет осуществляться на версии, которой нет в приложенных файлах: gurgen_7)
3) Версию gurgen_0 - можно считать эталонной, остальные версии могут содержать ошибки.
