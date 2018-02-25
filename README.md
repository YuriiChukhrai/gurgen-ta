# gurgen-ta

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
