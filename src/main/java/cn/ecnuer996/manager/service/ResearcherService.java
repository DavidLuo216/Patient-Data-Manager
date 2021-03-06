package cn.ecnuer996.manager.service;

import cn.ecnuer996.manager.model.Patient;
import cn.ecnuer996.manager.model.Researcher;
import org.springframework.data.domain.Page;

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
    public void updateResearcherInfo(Researcher researcher);

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

    /**
     * 忽略null
     * @param researcher
     * @return
     */
    Page<Researcher> findByExample(Researcher researcher, int pageIndex, int pageSize);

    void updateIsProhibited(Researcher researcher,Boolean bool);

}
