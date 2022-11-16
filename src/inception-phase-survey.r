# Inception Phase Survey Analysis

output_dim <- c("w" = 800, "h" = 800) # default settings for output dimensions
input_path <- "db/inception-phase-survey/"
out_path   <- "out/inception-phase-survey/"

data <- read.csv(paste(input_path, "inception-phase-survey.csv", sep = ""))

# create a dictionary from the data,
# each key is a question and each value is a list of answers

data_dict <- list()
for (col in names(data)) {
    data_dict[[col]] <- data[[col]]
}

# read the questions from inception-phase-survey-questions.txt
raw_questions <- readLines(paste(input_path, "inception-phase-survey-questions.txt", sep = "")) # nolint

questions <- list()

# create a dictionary from the questions,
# each key is 'q$' and each value is the mapped question

idx <- 1
for (current_question in raw_questions) {
    key <- paste("q", idx, sep = "")
    formatted_question <- paste(idx, ". ", current_question, sep = "")
    questions[[key]] <- formatted_question
    idx <- idx + 1
}

# ------------------------- #
# functions for the analysis

# function to generate the color_palette
generate_color_palette <- function(n, alpha) {
    color_palette <- rainbow(n)
    for (i in seq_along(color_palette)) {
        color_palette[i] <- adjustcolor(color_palette[i], alpha.f = alpha)
    }
    color_palette
}

# function to generate the relative frequency of a data column
calculate_relative_percentage <- function(data) {
    data <- round(data / sum(data) * 100, 2)
    paste(data, "%", sep = "")
}

# ------------------------- #

# for each column, generate a pie chart based on the
# frequency of each value within that column

for (col in names(data)) {
    # generate a data frame from the column
    col_data <- data.frame(data[[col]])

    # generate a frequency table from the data frame
    col_freq <- table(col_data)

    # parse the question from the questions dictionary
    f_question <- questions[[col]]

    # calculate the relative percentage of each value
    relative_percentage <- calculate_relative_percentage(col_freq)

    png(paste(out_path, col, ".png", sep = ""),
        width  = output_dim[["w"]],
        height = output_dim[["h"]]
    )

    # generate the colors
    color_palette <- generate_color_palette(length(col_freq), 0.85)

    # generate pie chart(s), assign relative percentage to each slice,
    # construct legend
    
    pie(col_freq, labels = relative_percentage, main = f_question,
        col = color_palette, border = color_palette, cex = 0.8)

    legend("topright", legend = names(col_freq), cex = 0.8, fill = color_palette) # nolint
    dev.off()
}