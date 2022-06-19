package jp.co.csj.backsys.mapper.original;

import jp.co.csj.backsys.po.original.Person;

public interface PersonMapper {
    /**
     *
     * @mbg.generated 
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 
     */
    int insert(Person row);

    /**
     *
     * @mbg.generated 
     */
    int insertSelective(Person row);

    /**
     *
     * @mbg.generated 
     */
    Person selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 
     */
    int updateByPrimaryKeySelective(Person row);

    /**
     *
     * @mbg.generated 
     */
    int updateByPrimaryKey(Person row);
}