[1]
Intergovernmental Panel on Climate Change (IPCC). (2021). Climate
Change 2021: The Physical Science Basis. Cambridge University Press.
[2]
United Nations. (2015). Transforming Our World: The 2030 Agenda for
Sustainable Development.
[3]
Cence Power. (n.d.). kWh to CO2 Calculator. Retrieved from
https://www.cencepower.com/calculators/kwh-to-co2-calculator
[4]
ModeShift. (n.d.). Why Public Transportation is Better than Cars.
Retrieved from https://www.modeshift.com/why-is-public-transportation-
better-than-cars
[5]
UCL Discovery. (2016). Flying and Carbon Emissions. Retrieved from
https://discovery.ucl.ac.uk/id/eprint/1477564/
[6]
Schafer, A. (2016). Long-Term Trends in Domestic US Passenger Travel.
Nature Climate Change.
[7]
ScienceDirect. (2023). Environmental Impacts of Consumer Electronics.
Retrieved
from
https://www.sciencedirect.com/science/article/abs/pii/S0308596123002124
[8]
ISO. (2006). Environmental Management—Life Cycle Assessment—
Principles and Framework (ISO 14040).
[9] Schäfer, A. W., Evans, A. D., Reynolds, T. G., & Dray, L. (2016). Costs of
mitigating CO₂ emissions from passenger aircraft. Nature Climate Change,
6(4), 412–417. https://discovery.ucl.ac.uk/id/eprint/1477564/
[10]
European Environment Agency. (2020). Energy and Environment Report.
[11]
Pan, S. & Van Fan, Y. (2021). Data-Driven Approaches to Greenhouse
Gas Emissions Reduction. Renewable and Sustainable Energy Reviews, 135,
110223.
[12] Sharma, R., & Gupta, P. (2019). Carbon intensity of the Indian electricity
grid: An assessment of CO₂ emissions. Energy Policy, 123, 56–63.
[13] U.S. Environmental Protection Agency (EPA) – Greenhouse Gas Emissions
from a Typical Passenger Vehicle.
[14] Natural Resources Canada. (n.d.). AutoSmart Fact Sheet 6: Fuel Efficient
Technologies
for
Vehicles.
Retrieved
from
https://natural-
resources.canada.ca/sites/www.nrcan.gc.ca/files/oee/pd


page 2, 2. Related works  World Values Survey reports reference?
page 4, left side, CO2 ko proper write kro
data source links ?, survey reference ? 

page 5 , (4 system analysis)  
    formulas should properly written in fraction (mod-1 {2 , 3 , 4}), (mod-2 {6}), (mod-3 ), final score

page 9, graph 9 , are there no distribution from 0 - 5, 
    frequency soo low showing data size is small



flow chart ,, missing data handling


machine algo ? 


integrative architecture , how





feature engineering”?

Does every parameter get the same weight? Are some more important than others? Don’t leave us hanging on how you’re building this sustainability score.

. Ranting About ‘Related Works’ without a Point

    "Carbon footprint calculators typically depend on generalized national averages or on some simplifications of household consumption patterns."

        Sure, we’ve heard it. But what’s your point? You mention that others are using generalized data, but you don’t show how your model improves upon this yet. This section feels like you're just dropping names of issues without linking them to how your solution is specifically different or better. It sounds like a cheap way to pad the paper, and doesn’t do anything to build the credibility of your own model.

we are presenting research paper, i have been alloted  5. COMPARISON OF RESULTS, and PERFORMANCE AND RESULTS
 
 
 
 As comparison table, this outlines the key features of existing sustainability tools to the proposed model, particularly data sources, applicability, entry into AI, format for output or score, use difficulties, paywall status, and overall availability.[11] Both the WWF and CarbonFootprint.com depend on self-user reports through online calculators, thus giving access to the whole world, but their computation of results tends to be a rather static rather non-AI approach. Oroeco and My Carbon Action work with self- reports combined with some levels of machine learning (the check mark in the AI-Based column indicates this) for their insights into lifestyle patterns. However, there are also some limitations regarding the global applicability and ease of use of the tools. On the other hand, it has an edge over the other because it takes in multi-factor input into a unified AI-powered system, that is to say, covering electricity, transport, recycling, and tech habits. Therefore, it would include complex analytics and global applicability to offer a more nuanced sustainability score-without- boll, thus making it an accessible and comprehensive view of personal environmental impact.
Table 3:”Comparison with Various Pre-Available Models”

Yayzy is an online sustainability tool that calculates one's carbonfootprint and gives users an overview of the environmental impact.Primarily, it uses a fixed and predetermined set of formulae andinput variables to produce quick results. In contrast, the HumanSustainability Score model adopts a multi-layered AI-poweredapproach that considers a very broad set of lifestyle variables intoone score, from electricity consumption (factoring for solarpanels), to detailed transportation metrics (including vehicleownership, fuel type, and flight travel), recycling behavior, andtech purchases. Yayzy relies more on static calculations; ourmodel, on the other hand, utilizes logistic scaling functions withinmodules so that non-linear alterations mirroring the truecumulative impact of personal actions can be accounted.Furthermore, our system is set with the intention of a multi-AImodal system, where the user can choose among the modes fordifferent analytical routes depending on the richness and type ofavailable data. Besides that, our model is envisioned to considersome macro-environmental factors like region-specific data anddistrict-wise GDP, which will provide a contextual layer fromwhich both personal and policy interventions can be informed.Thus, in all, Yayzy is a very handy and useful baseline assessment,while our approach is adaptive, modular, and data-rich, allowingfor a more subtle, scalable, and actionable sustainability
Table 4:”Yayzy vs Our Model”
Figure 4: “Yayzy UI Complexity”
 
 These days, it is considered standard practice, and often required by demand, to have tools with sustainability assessments paid with money. Most of these platforms are subscription-based or charge for licensing fees, as they have involved a huge investment in data, advanced analytical methods, and continuous refinement of models. The costs of procuring high-quality datasets from diverse sources, developing trusted AI algorithms, and ensuring compliance with regulations are heavy. Oftentimes, hence, the market has shifted to paid products, which in turn ensure the sustainability of a service provider who may also offer insights that tend to be more accurate, customizable, and up to date. Users can take advantage of features, such as real-time on-the-ground data integration, regional analytics, and personalized recommendations, that usually come as add-ons to the free versions. Further, such monetization would ensure the possibility of continuous research and development for further improvements and innovation in the fields of sustainability assessment. 

PERFORMANCE AND RESULTS
Graph 5:”Model Performance Comparison” 
The bar chart illustrates how the four machine learning models - Linear Regression, Decision Tree, Random Forest, and Support Vector Machine (SVM) - are evaluated in terms of the Mean Squared Error (MSE). The less the MSE, the better is the accurate prediction. Between all models, Random Forest has the lowest MSE, therefore, it is considered the best model in this comparison. Compared with errors of Linear Regression and Decision Tree, Decision Tree performs slightly worse. SVM has the highest MSE by a wide margin, indicating that it is poor performance on this dataset, which is likely to be due to improper hyperparameter tuning, nature of the data, or SVM not being well suited for this specific problem. All in all, the results impelled that Random Forest is the most suitable model for this dataset while SVM needs pieces of further improvement or reconsideration. performance is the Mean R², indicated within each subplot. This value ranges from 0 to 1 and measures goodness of fit; the higher the value, the better the predictive accuracy. The exceptional model evidenced here by the highest R² of linear regression, 0.68, suggests that this model is the most competent to make this prediction. The model of the decision tree, conversely, has produced an R² of 0.06, indicating a very poor fit for this application. SVR and Gradient Boosting performed moderately well, attaining values of 0.51 and 0.48 R², which may reflect reasonable but not outstanding predictive capability. Random Forest and KNN exhibit a moderate predictive power of 0.39 and 0.35 R², respectively, just below that of SVR and Gradient Boosting, albeit with the same predictive worth. The scatter plots back this numerical exposition, with linear regression showing the best fit of observation points to the diagonal line while Decision Trees dispersed most widely. Overall, this image gives a clear overview focused on comparing the models' predictive accuracy and underlining the importance of model choice on the basis of performance metrics and visual fit. Graph 7:”Multi Model Performance Comparison” Graph 6:”Multi Model Score Comparison” In this image, six distinct machine learning models—Support Vector Regression (SVR), Decision Tree, Random Forest, Gradient Boosting, Linear Regression, and K-Nearest Neighbors (KNN)—are judged against each other on predicting "Sustainability Scores." The visual consists of a 2x3 grid with each subplot representing one model. Every subplot features a scatter plot with the actual sustainability score on the x-axis and the corresponding model's predicted score on the y-axis. A red dashed diagonal line is drawn across all subplots, serving as a criterion for perfect prediction where both actual and predicted scores are in perfect concordance. The primary measurement of model This image shows a comparative study of six machine-learning models: Support Vector Regression (SVR), Decision Tree, Random Forest, Gradient Boosting, Linear Regression, and K- Nearest Neighbors (KNN) with respect to mean R² and mean absolute error or mean MAE. The grouped bar chart representation has two adjacent bars, one for Mean R² (light blue) and the other for Mean MAE (light green), each for a model. The left y-axis describes the Mean R² ranging from 0 to 0.7, while the right y-axis describes the Mean MAE ranging from 0 to 1. The model names can be read on x-axis placed at 45-degree angle for the ease of reading. The title given to this chart is "Comparison in model performance," which adequately summarizes its use. Notably, Linear regression performs best with the highest Mean R² value of about 0.68 coupled with low Mean MAE (around 0.37), showing more accurate prediction with a minor error amount. In fact, concerning Mean R² (close to 0) as well as Mean MAE (near 1.1), the Decision Tree model shows the poorest performance. On the other hand, models like SVR, Random Forest, Gradient Boosting, and KNN exhibit moderate performance, with Mean R² values lying between the ranges of 0.35-0.51 and Mean MAE values between 0.45-0.8. This chart vividly represents trade-offs of effective predictive ability versus errors for various models and makes Linear Regression the most effective model for this particular task.
model is represented by a box displaying the distribution of its residual errors, calculated as actual values minus predicted values. Residual error is plotted on the y-axis from -2 to +2, while model names line the x-axis. A reference point for zero error is shown with a red dashed horizontal line at y=0. The title "Distribution of Residual Errors by Model" makes it evident what the chart is about. A box plot shows the availability, median and possible outliers of the residual error for each model. It clearly specifies that Linear Regression and Gradient Boosting are the models that indicate the least level of breadth in their boxes, which would mean variations are significantly lower in their errors, and thus they may be more consistent. The accuracy of the Decision Tree is lower and varies greatly since its range and median error is much wider. SSVD, RF, and KNN showed mild spreads and medians. The position of the boxes against the zero-error line indicates the tendency of the models to over or under-predicted. It gives insight into the distribution of errors per model and provides a general picture of their consistencies and accuracies in failure. Graph 8:”Multi Model Distribution Graph for Residual Errors” This image shows a comparative analysis of the distribution of residual errors by six machine learning models: Support Vector Regression (SVR), Decision Tree, Random Forest, Gradient Boosting, Linear Regression, and K-nearest neighbors (KNN). All models will be exercised based on how each one is illustrated by the boxplot. Each of them forms a box in the image, which shows the model's distribution of residual errors, which is equal to the discrepancy between the actual and predicted values. The y-axis explains how this discrepancy varies from about -2 to 2, while the x-axis is made up of the names of the different models. A red dashed horizontal line is found at y=0 to show that it is the level under consideration with regard to error value. The title "Distribution of Errors for Each Model" makes it clear what the information in this chart will cover. Boxplots then show the distribution, median, as well as the extremes of residual error values by each model. It can be inferred that Linear Regression and Gradient Boosting have the least breadth in their boxes which indicate lower variance in their errors and for that reason, considered as more consistent. On the contrary, the Decision Tree model presents a much wider range and a higher median error, exposing lesser accuracy and more variance. Finally, moderate spreads and medians are presented for SVR, Random Forest, and KNN, revealing their spreading tendency. The positioning of those boxes against the zero-error line, however, indicates the tendency that each model has of over or under prediction. In general, this figure helps give an idea of the errors distributions for each model and gives an overview of their respective consistencies and accuracies in prediction. This image shows a comparative description of the distribution of residuals across six machine learning models, namely, Support Vector Regression (SVR), Decision Tree, Random Forest, Gradient Boosting, Linear Regression, and K-Nearest Neighbors (KNN). Visualization is conducted in a boxplot in which each Graph 9:”Score Distribution of Our Data” The histogram illustrates the distribution of Computed Sustainability Scores. The title of the chart makes clear its aim: Distribution of Computed Sustainability Scores. The x-axis is labeled Computed Sustainability Score, with a range stretching from 5 to 10 along the computed score line. The y-axis has the label of Frequency, indicating how many times a score appeared in the data set. The bins of the histogram are divided into 10 spread-out sustainability scores. All bars are light orchid-colored, with an outer black border for easy identification of the bins. The data highlight clustering around a score that returned a greatest frequency of 5. Frequencies then continue to decline on either side of 7, indicating that scores near 7 were more frequently observed in the dataset. Scores from 5 to 6 and from 9 to 10 hold least frequencies, indicating that such extreme values were rare. In all, the histogram has done a great job visually analyzing the distribution of computed sustainability scores, showing central tendency with some spread of the data.10 It provides a holistic assessment about varying performances of machine learning models in predicting "Sustainability Scores" together with a distributional overview of the scores themselves. The first scatter plot indicates that Linear Regression predictions are indeed the best because of the high R² and prediction actual score corresponds closely, while Decision Tree model prediction is the least effective. The bar plot showing Mean R² and Mean MAE strengthened this finding and indicated superior performance by Linear Regression and the failure of the decision tree. Next, the box plots showing residual errors or deviations depicted the consistency and variance in prediction associated with these models and brought out relative stability of errors in predictions by Linear Regression and Gradient Boosting as models. However, the last histogram showed computations from each of the computed sustainability scores, revealing convergence of scores around 7 to indicate central tendency. All in all, these images comprehensively evaluate model performance whilst providing a better understanding of predicted variable distribution, iterating the significance of model selection and learning out of prediction error and data distributio



"Our model isn’t just reactive—it’s predictive and contextualized."

"Compared to traditional tools, we’re offering an AI-first, globally scalable solution."
create a one-page printable version or a slide-ready FAQ cheat sheet for this?
"Model performance proved that Linear Regression, despite its simplicity, outperformed others on interpretability and accuracy."

"Our aim is to make sustainability scoring as personalized and reliable as credit scoring—easy to understand, actionable, and data-driven."