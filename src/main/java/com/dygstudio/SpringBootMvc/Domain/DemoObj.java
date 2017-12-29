package com.dygstudio.SpringBootMvc.Domain;

/**
 * @author: diyaguang
 * @date: 2017/12/28 下午1:58
 * @description: com.dygstudio.SpringBootMvc.Domain
 */
public class DemoObj {
    private Long id;
    private String name;

    public DemoObj(){
        super();
    }
    public DemoObj(Long id,String name){
        super();
        this.id = id;
        this.name = name;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}
}
