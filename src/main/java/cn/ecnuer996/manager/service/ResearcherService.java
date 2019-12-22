package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.Researcher;
import java.util.List;

public interface ResearcherService {


//    public
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
     * 返回指定职位Researchers
     * @param roles
     * @return
     */
    public List<Researcher> findResearchersByRoles(List<String> roles);

    /**
     * 列出所有
     * @return
     */
    public List<Researcher>findAll();
}
