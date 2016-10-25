#import necessary packages
library('randomForest')
library('ggplot2')

#set working directory
setwd("/users/andrewtrice/Desktop/Data Science Capstone")

#import datasets from working directory
train <- read.csv("train.csv") #use nrows=1000 rows for speed during feature engineering
test <- read.csv("test.csv") #use nrows=1000 rows for speed during feature engineering

str(train)

#factorize training set
train_factor <- train
train_factor$weather <- factor(train$weather)
train_factor$holiday <- factor(train$holiday)
train_factor$workingday <- factor(train$workingday)
train_factor$season <- factor(train$season)

#factorize test set
test_factor <- test
test_factor$weather <- factor(test$weather)
test_factor$holiday <- factor(test$holiday)
test_factor$workingday <- factor(test$workingday)
test_factor$season <- factor(test$season)

#create time column by stripping out timestamp
train_factor$time <- substring(train$datetime,12,20)
test_factor$time <- substring(test$datetime,12,20)

#factorize new timestamp column
train_factor$time <- factor(train_factor$time)
test_factor$time <- factor(test_factor$time)

#create day of week column
train_factor$day <- weekdays(as.Date(train_factor$datetime))
train_factor$day <- as.factor(train_factor$day)
test_factor$day <- weekdays(as.Date(test_factor$datetime))
test_factor$day <- as.factor(test_factor$day)

aggregate(train_factor[,"count"],list(train_factor$day),mean)

#create Sunday variable
train_factor$sunday[train_factor$day == "Sunday"] <- "1"
train_factor$sunday[train_factor$day != "1"] <- "0"

test_factor$sunday[test_factor$day == "Sunday"] <- "1"
test_factor$sunday[test_factor$day != "1"] <- "0"

#convert to factor
train_factor$sunday <- as.factor(train_factor$sunday)
test_factor$sunday <- as.factor(test_factor$sunday)

#convert time and create $hour as integer to evaluate for daypart
train_factor$hour<- as.numeric(substr(train_factor$time,1,2))
test_factor$hour<- as.numeric(substr(test_factor$time,1,2))

#create daypart column, default to 4 to make things easier for ourselves
train_factor$daypart <- "4"
test_factor$daypart <- "4"

#4AM - 10AM = 1
train_factor$daypart[(train_factor$hour < 10) & (train_factor$hour > 3)] <- 1
test_factor$daypart[(test_factor$hour < 10) & (test_factor$hour > 3)] <- 1

#11AM - 3PM = 2
train_factor$daypart[(train_factor$hour < 16) & (train_factor$hour > 9)] <- 2
test_factor$daypart[(test_factor$hour < 16) & (test_factor$hour > 9)] <- 2

#4PM - 9PM = 3
train_factor$daypart[(train_factor$hour < 22) & (train_factor$hour > 15)] <- 3
test_factor$daypart[(test_factor$hour < 22) & (test_factor$hour > 15)] <- 3

#convert daypart to factor
train_factor$daypart <- as.factor(train_factor$daypart)
test_factor$daypart <- as.factor(test_factor$daypart)

#convert hour back to factor
train_factor$hour <- as.factor(train_factor$hour)
test_factor$hour <- as.factor(test_factor$hour)

#get rid of weather 4
train$weather[train$weather==4] <- 3
test$weather[test$weather==4] <- 3

#variables
myNtree = 500
myMtry = 7
myImportance = TRUE

#set the seed to mean for model reproducibility 
set.seed(192)

#fit and predict casual
casualFit <- randomForest(casual ~ season + holiday + workingday + weather + temp + atemp + humidity + hour, data=train_factor, ntree=myNtree, mtry=myMtry, importance=myImportance)
predictCasual <- predict(casualFit, test_factor)

#fit and predict registered
registeredFit <- randomForest(registered ~ season + holiday + workingday + weather + temp + atemp + humidity + hour, data=train_factor, ntree=myNtree, mtry=myMtry, importance=myImportance)
predictRegistered <- predict(registeredFit, test_factor)

#add both columns into final count, round to whole number
test$count <- casualFit + test$registered

#testplot
plot(train$count)
plot(test$count)

#write output to csv for submission
submit <- data.frame (datetime = test$datetime, count = test$count)
write.csv(submit, file = "randomForest_Prediction.csv", row.names=FALSE)

#label variable importances for both casual and registered
imp1 <- importance(casualFit, type=1)
imp2 <- importance(registeredFit, type=1)
featureImportance1 <- data.frame(Feature=row.names(imp1), Importance=imp1[,1])
featureImportance2 <- data.frame(Feature=row.names(imp2), Importance=imp2[,1])

#plot variable importances
casualImportance <- ggplot(featureImportance1, aes(x=reorder(Feature, Importance), y=Importance)) +
  geom_bar(stat="identity", fill="blue") +
  coord_flip() + 
  theme_light(base_size=20) +
  xlab("") +
  ylab("") + 
  ggtitle("Casual Rental Feature Importance\n") +
  theme(plot.title=element_text(size=18))

registeredImportance <- ggplot(featureImportance2, aes(x=reorder(Feature, Importance), y=Importance)) +
  geom_bar(stat="identity", fill="blue") +
  coord_flip() + 
  theme_light(base_size=20) +
  xlab("") +
  ylab("") + 
  ggtitle("Registered Rental Feature Importance\n") +
  theme(plot.title=element_text(size=18))
