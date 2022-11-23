/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.vul_prediction;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.filters.Filter;


public class Pred_Model {
  /** the classifier used internally */
  protected Classifier m_Classifier = null;

  /** the filter to use */
  protected Filter m_Filter = null;

  /** the training file */
  protected String m_TrainingFile = null;

  /** the training instances */
  protected Instances m_Training = null;

  /** for evaluating the classifier */
  protected Evaluation m_Evaluation = null;

  /**
   * initialization
   */
  public Pred_Model() {
    super();
  }

  /**
   * sets the classifier to use
   * 
   * @param name the classname of the classifier
   * @param options the options for the classifier
   */
  public void setClassifier(String name, String[] options) throws Exception {
    m_Classifier = AbstractClassifier.forName(name, options);
  }

  /**
   * sets the filter to use
   * 
   * @param name the classname of the filter
   * @param options the options for the filter
   */
  public void setFilter(String name, String[] options) throws Exception {
    m_Filter = (Filter) Class.forName(name).newInstance();
    if (m_Filter instanceof OptionHandler) {
      ((OptionHandler) m_Filter).setOptions(options);
    }
  }

  /**
   * sets the file to use for training
     * @param name
     * @throws java.lang.Exception
   */
  public void setTraining(String name) throws Exception {
    m_TrainingFile = name;
    m_Training = new Instances(new BufferedReader(
      new FileReader(m_TrainingFile)));
    m_Training.setClassIndex(m_Training.numAttributes() - 1);
  }

  /**
   * runs 10fold CV over the training file
     * @param Fold_Number
     * @throws java.lang.Exception
   */
  public void execute(int Fold_Number) throws Exception {
    // run filter
    m_Filter.setInputFormat(m_Training);
    Instances filtered = Filter.useFilter(m_Training, m_Filter);
    // train classifier on complete file for tree
    m_Classifier.buildClassifier(filtered);

    // 10fold CV with seed=1
    m_Evaluation = new Evaluation(filtered);
    m_Evaluation.crossValidateModel(m_Classifier, filtered, Fold_Number,
      m_Training.getRandomNumberGenerator(1));
  }

  /**
   * outputs some data about the classifier
   */
  @Override
  public String toString() {
    StringBuffer result;

    result = new StringBuffer();

    result.append("Classifier...: ").append(Utils.toCommandLine(m_Classifier)).append("\n");
    if (m_Filter instanceof OptionHandler) {
      result.append("Filter.......: ").append(m_Filter.getClass().getName()).append(" ").append(Utils.joinOptions(((OptionHandler) m_Filter).getOptions())).append("\n");
    } else {
      result.append("Filter.......: ").append(m_Filter.getClass().getName()).append("\n");
    }
    result.append("Training file: ").append(m_TrainingFile).append("\n");
    result.append("\n");

    result.append(m_Classifier.toString()).append("\n");
    result.append(m_Evaluation.toSummaryString()).append("\n");
    try {
      result.append(m_Evaluation.toMatrixString()).append("\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      result.append(m_Evaluation.toClassDetailsString()).append("\n");
    } catch (Exception e) {
    }
    result.append("\n");
    result.append("False Negative Rate : ").append(m_Evaluation.weightedFalseNegativeRate()).append("\n");
    result.append("False Positive Rate : ").append(m_Evaluation.weightedFalsePositiveRate()).append("\n");
    result.append("FMeasure : ").append(m_Evaluation.weightedFMeasure()).append("\n");
    result.append("AreaUnderROC : ").append(m_Evaluation.weightedAreaUnderROC()).append("\n");
    

    return result.toString();
  }

 
}