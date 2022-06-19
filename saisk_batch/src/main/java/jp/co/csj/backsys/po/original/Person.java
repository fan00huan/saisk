package jp.co.csj.backsys.po.original;

import java.util.Date;

/**
 * person()
 * @author tech 
 */
public class Person {
    /**
     *  id
     */
    private Integer id;

    /**
     *  name
     */
    private String name;

    /**
     *  updatetime
     */
    private Date updatetime;

    /**
     * 
     * @author tech
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @author tech
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @author tech
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @author tech
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @author tech
     * @return updatetime 
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     * @author tech
     * @param updatetime 
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     *
     * @mbg.generated 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}