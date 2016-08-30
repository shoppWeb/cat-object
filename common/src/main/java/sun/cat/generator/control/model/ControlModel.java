package sun.cat.generator.control.model;

/**
 * service中间model
 * Created by 小小工作者 on 2016/6/12.
 */
public class ControlModel {
    private EntityModel entity;//实体
    private String control;//service

    public class EntityModel{
        private String entityName;
        private String entityIdName;//实体主键名称
        private Object entityIdType;//实体主键类型

        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }

        public String getEntityIdName() {
            return entityIdName;
        }

        public void setEntityIdName(String entityIdName) {
            this.entityIdName = entityIdName;
        }

        public Object getEntityIdType() {
            return entityIdType;
        }

        public void setEntityIdType(Object entityIdType) {
            this.entityIdType = entityIdType;
        }
    }

    public EntityModel getEntity() {
        return entity;
    }

    public void setEntity(EntityModel entity) {
        this.entity = entity;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }
}
