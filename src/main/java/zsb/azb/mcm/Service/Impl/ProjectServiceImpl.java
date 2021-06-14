package zsb.azb.mcm.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.ProjectMapper;
import zsb.azb.mcm.Model.ProjectEntity;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Service.ProjectService;
import zsb.azb.mcm.Utils.ResponseManager;

@Service
public class ProjectServiceImpl
        implements ProjectService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private TokenPojo tokenPojo;

    public ResponsePojo projectListByToken()
    {
        int userId = this.tokenPojo.getUserId();

        List<ProjectEntity> projectList = this.projectMapper.listProjectByUser(userId);

        return ResponseManager.buildSuccess(projectList);
    }

    public ResponsePojo projectListByUser(int userId)
    {
        List<ProjectEntity> projectList = this.projectMapper.listProjectByUser(userId);
        return ResponseManager.buildSuccess(projectList);
    }

    public ResponsePojo insertUserProject(int userId, int projectId)
    {
        try
        {
            this.projectMapper.insertUserProject(userId, projectId);
            return ResponseManager.buildSuccess("保存成功");
        }
        catch (Exception ex) {}
        return ResponseManager.buildError(12001, "新增的资源已存在");
    }

    public ResponsePojo deleteUserProject(int userId, int projectId)
    {
        try
        {
            if (this.projectMapper.deleteUserProject(userId, projectId) > 0) {
                return ResponseManager.buildSuccess("删除成功");
            }
            return ResponseManager.buildError(12002, "删除的资源不存在");
        }
        catch (Exception ex) {}
        return ResponseManager.buildError(12002, "删除的资源不存在");
    }
}
