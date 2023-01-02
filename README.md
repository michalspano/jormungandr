## Mini Project Group 10 ~ _'Jörmungandr'_ ~ `stats`
 
The `stats` branch contains `R-lang` source with __statistical__ modules used for the __project report__. The plots, figures generated are included in the __Project Management Report__. This way, we have been able to automatize the generation of the plots and figures, and thus, we have been able to generate the report in a reproducible way. Furthermore, we have used the same script throughout the phases, making it easier to maintain and update.

### Inception Phase Survey Results

| phase                 |         results         |
|:----------------------|:-----------------------:|
| 1. Inception Phase    | [link][survey1-results] |
| 2. Elaboration Phase  | [link][survey2-results] |
| 3. Construction Phase | [link][survey3-results] |
| 4. Transition Phase   | [link][survey4-results] |

<!-- LINKS -->
[survey1-results]: https://docs.google.com/spreadsheets/d/15I0KnlatvpiJ8o5Dh-DS_xJyulmXFIdCkNrLt8DK_IY/edit?usp=sharing
[survey2-results]: https://docs.google.com/spreadsheets/d/1FsK14F4H2ZkqL69rZfpRrcCHpH8UK4UvR2ph2kJ8k58/edit?usp=sharing
[survey3-results]: https://docs.google.com/spreadsheets/d/1gE2rQLclGyox2gH7__hz1gBgbmZgLYti4RvBzBDWuXI/edit?usp=sharing
[survey4-results]: todo.com

### Understanding the Data and the Structure

The `db` directory contains the raw gathered data to be analyzed. Each __sub-directory__ refers to a particular phase of the project, such as: `db/inception-phase-survey`. The data is stored in `csv` format, and it is organized in a way that each row represents a single survey response, and each column represents a single question. The data is also stored in a way that it is easy to be read by the `R` programming language, and it is easy to be analyzed and visualized. Then, under the `out` directory, the figures, plots, etc. are stored. Similarly to the structure of the `db` folder, the `out` folder contains a __sub-directory__ for each phase of the project with the figures, plots, etc. generated by the `R` scripts. Ultimately, the `src` folder, as the name suggests, contains the __source code__ of the `R` scripts used.

### The `R` Programming Language in Relation to the Project

The use of the `R` programming language was not a requirement for this project (and the course); however, we have agreed that it can complement the report portion of the project positively. Given that our of out team members, Michal, had been working with such a tool previously, we were able to integrate it into our project management workflow seamlessly. The `R` language is a powerful tool for statistical analysis and data visualization. It is also a free and open source software, which makes it a good choice for our project. The `R` language is also widely used in the industry, and it is a good tool to learn for future projects.

### What could be improved?

The team members have decided that, for future projects, they might consider making use of the `R` programming more frequently, not only to create statistical figure, but to also perform statistical analysis on the data. This would allow the team to make more informed decisions, and to have a better understanding of the data they are working with. That is, some planning at time management could have been enhanced by the use of simple statistical analysis, namely in an `R`-based environment.