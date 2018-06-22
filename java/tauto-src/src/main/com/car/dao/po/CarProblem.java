package main.com.car.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CarProblem implements Serializable{
    /**
     * 
     */
    private Integer problemId;

    /**
     * 问题
     */
    private String problemProblem;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 
     */
    private Date createDate;

    /**
     * 解答
     */
    private String problemAnswer;

    /**
     * 
     * @return problem_id 
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * 
     * @param problemId 
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    /**
     * 问题
     * @return problem_problem 问题
     */
    public String getProblemProblem() {
        return problemProblem;
    }

    /**
     * 问题
     * @param problemProblem 问题
     */
    public void setProblemProblem(String problemProblem) {
        this.problemProblem = problemProblem;
    }

    /**
     * 是否已删除
     * @return is_delete 是否已删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否已删除
     * @param isDelete 是否已删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 
     * @return create_date 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     * @param createDate 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 解答
     * @return problem_answer 解答
     */
    public String getProblemAnswer() {
        return problemAnswer;
    }

    /**
     * 解答
     * @param problemAnswer 解答
     */
    public void setProblemAnswer(String problemAnswer) {
        this.problemAnswer = problemAnswer;
    }
}