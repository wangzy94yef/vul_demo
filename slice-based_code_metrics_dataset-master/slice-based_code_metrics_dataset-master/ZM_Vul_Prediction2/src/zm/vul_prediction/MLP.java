/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.vul_prediction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.filters.Filter;

/**
 *
 * @author zm
 */
public class MLP {
    
    protected String Options = null; //MLP options
    protected String Train_Dataset_FN = null; //File Name of the train Dataset
    //protected String Test_Dataset_FN = null; //File Name of the test Dataset
    protected MultilayerPerceptron Mlp = null ; // the MLP
    protected Filter m_Filter = null;
    
    
    public void Build_MLP(boolean Balance_Dataset) throws FileNotFoundException, Exception{
        
        FileReader Train_Reader = new FileReader(Train_Dataset_FN);
        Instances Train_Instances = new Instances(Train_Reader);
        Train_Instances.setClassIndex(Train_Instances.numAttributes() - 1);
        
        m_Filter = (Filter) Class.forName("weka.filters.supervised.instance.SpreadSubsample").newInstance();
        //m_Filter = (Filter) Class.forName("weka.filters.supervised.instance.SpreadSubsample -M 0.0 -X 0.0 -S 1").newInstance();
        
        String[] Filter_Options = Utils.splitOptions("");
        
        if (Balance_Dataset)
        {
            Filter_Options = Utils.splitOptions("-M 1.0 -X 0.0 -S 1");
        }
        else
        {
            Filter_Options = Utils.splitOptions("");
        }
        
        if (m_Filter instanceof OptionHandler) {
        ((OptionHandler) m_Filter).setOptions(Filter_Options);
        }
        m_Filter.setInputFormat(Train_Instances);
        Instances Filtered_Instances = Filter.useFilter(Train_Instances, m_Filter);
        
    // train classifier on complete file for tree
        Mlp = new MultilayerPerceptron();
        Mlp.setOptions(Utils.splitOptions(Options));
        Mlp.buildClassifier(Filtered_Instances);
        Train_Instances.clear();
    }
    
    public String Evaluate_MLP_CV (int Num_Folds, boolean Balance_Dataset) throws Exception{
        
        StringBuffer Results = new StringBuffer();
        
        FileReader Train_Reader = new FileReader(Train_Dataset_FN);
        Instances Train_Instances = new Instances(Train_Reader);
        Train_Instances.setClassIndex(Train_Instances.numAttributes() - 1);
        
        m_Filter = (Filter) Class.forName("weka.filters.supervised.instance.SpreadSubsample").newInstance();
        //m_Filter = (Filter) Class.forName("weka.filters.supervised.instance.SpreadSubsample -M 0.0 -X 0.0 -S 1").newInstance();
        
        String[] Filter_Options;
        
        if (Balance_Dataset)
        {
            Filter_Options = Utils.splitOptions("-M 1.0 -X 0.0 -S 1");
            //Filter_Options = Utils.splitOptions("");
        }
        else
        {
            Filter_Options = Utils.splitOptions("");
        }
        
        if (m_Filter instanceof OptionHandler) {
        ((OptionHandler) m_Filter).setOptions(Filter_Options);
        }
        m_Filter.setInputFormat(Train_Instances);
        Instances Filtered_Instances = Filter.useFilter(Train_Instances, m_Filter);
        
        Evaluation Eval = new Evaluation(Train_Instances);
        Eval.crossValidateModel(Mlp, Filtered_Instances, Num_Folds, new Random(1));
        Train_Reader.close();
        
        Results.append(Eval.toSummaryString()).append("\n");
        Results.append(Eval.toMatrixString()).append("\n");
        Results.append(Eval.toClassDetailsString()).append("\n");
        Results.append("False Negative Rate : ").append(Eval.weightedFalseNegativeRate()).append("\n");
        Results.append("False Positive Rate : ").append(Eval.weightedFalsePositiveRate()).append("\n");
        Results.append("FMeasure : ").append(Eval.weightedFMeasure()).append("\n");
        Results.append("AreaUnderROC : ").append(Eval.weightedAreaUnderROC()).append("\n");
        
        
        return Results.toString();
    }
    
    public MLP (String Opts, String Trn_Dataset_FN/*, String Tst_Dataset_FN*/){
        
        Options = Opts; //MLP options
        Train_Dataset_FN = Trn_Dataset_FN; //File Name of the train Dataset
        //Test_Dataset_FN = Tst_Dataset_FN; //File Name of the test Dataset
        
    }

    
}
