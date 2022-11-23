# slice-based_code_metrics_dataset

In this repositury we make publically available a dataset contains 18 code metrics (the features) calculated from a set of labeled CSs taken from the public dataset available here : https://github.com/SySeVR/SySeVR/.
The actual version of the dataset contains the following metrics: • LOC metrics ( Physic lines: number of total lines, Empty lines: number of empty lines, Lines of comments: number of lines of comments, Lines of the program: number of program lines (directive, definition, declaration, commands...)). • McCabe Metrics (McCabe number: The cyclomatic complexity) • Halstead metrics (n1: number of distinct operators, n2: number of distinct operands, N1: total number of operators, N2: total number of operands, n: program vocabulary, N: program length, N’: calculated program length, V: Halstead volume, D: difficulty, E: effort, T: time required to program, B1: number of delivered Bugs 1, B2: number of delivered Bugs 2) The preparation of the dataset followed two steps. The first step consists of parsing the original dataset in order to separate each CS then calculate its code metrics. The second step consists of eliminating any redundant data. The final version proposed here contains 95351 instances.

Please cite the following paper if using the dataset or the source code in your research work :

M. Zagane, M. K. Abdi and M. Alenezi, "Deep Learning for Software Vulnerabilities Detection Using Code Metrics," in IEEE Access, vol. 8, pp. 74562-74570, 2020, doi: 10.1109/ACCESS.2020.2988557.
