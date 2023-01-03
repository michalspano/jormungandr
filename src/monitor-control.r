# Time analysis (estimate vs actual) for PMR

# since we run the R script as source, we don't pass command line arguments
# simulation of command-line arguments

iteration_number <- 10  # statically alter this value
span             <- 1   # typically for week
opacity          <- 0.6 # opacity factor of the lines

title     <- paste("Time Analysis (Estimate vs Actual) - Iteration ", iteration_number, sep = "")       # nolint
out_path  <- paste("out/monitor-and-control/monitor-and-control-", iteration_number, ".pdf", sep = "")  # nolint

in_path   <- paste("db/monitor-and-control/iteration", iteration_number, ".csv", sep = "") # nolint
in_data   <- read.csv(in_path, header = TRUE, sep = ",")

# interpret the data as numeric vectors
y1 <- as.numeric(in_data$estimate)
y2 <- as.numeric(in_data$actual)

# check if the vectors are of the same length - this is a precondition
if (length(y1) != length(y2)) {
  stop("The vectors must be of the same length")
}

# define x coordinates range
x <- seq(0, span, length.out = length(y1)) # x coordinates

# the y-axis has must contain the maximum value of both vectors
if (max(y1) > max(y2)) {
  temp <- y1
  y1 <- y2
  y2 <- temp
}

# estimated time + plot
plot(x, y2,
    col = adjustcolor("blue", alpha.f = opacity),
    lwd = 4,
    type = "l",
    xlab = "span (week)",
    ylab = "time (h)",
    main = title
  )

# actual time + plot
lines(x, y1,
    col = adjustcolor("red", alpha.f = opacity),
    lwd = 4
)

# legend to the plot
legend("topright",
      legend = c("Estimate", "Actual"),
      col = c("red", "blue"),
      lwd = 2
)

# generate the PDF plot
dev.copy2pdf(file = out_path,
            width = 10,
            height = 10,
            pointsize = 10
)

dev.off()
