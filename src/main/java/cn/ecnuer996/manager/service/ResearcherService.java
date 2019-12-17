package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.Researcher;
import java.util.List;

public interface ResearcherService {
    /**
     * 根据name get Resarcher
     * @param name 唯一标识
     * @return
     */
    public Researcher getResearcher(String name);

    /**
     * 删除
     * @param name
     */
    public void deleteResearcher(String name);

    /**
     * 更新
     * @param researcher
     */
    public void updateResearcher(Researcher researcher);

    /**
     * 添加
     * @param researcher
     */
    public Researcher saveResearcher(Researcher researcher);

    /**
     * 查找
     * @param name
     * @param roles
     * @return
     */
    public List<Researcher> findResearcher(String name,List<String> roles);

    /**
     * 列出所有
     * @return
     */
    public List<Researcher>  findAll();
}
