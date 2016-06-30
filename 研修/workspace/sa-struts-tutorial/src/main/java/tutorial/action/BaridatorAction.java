package tutorial.action;

//import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
//import org.seasar.framework.container.SingletonS2Container;
//import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

//import tutorial.entity.Baridator;
import tutorial.form.BaridatorForm;



public class BaridatorAction {

    public JdbcManager jdbcManager;

    @ActionForm
    @Resource
    protected BaridatorForm BaridatorForm;

    @Execute(validator = false)
    public String index() {

        BaridatorForm.initialized();

//        for (Baridator e : results) {
//            System.out.println(e.name);
//        }

        return "index.jsp";
    }

    @Execute(validator = false)
    public String question() {
        return "ask0.jsp";
    }

    @Execute(validator = false)
    public String woman() {
        BaridatorForm.answer[0] = "woman";
        return "ask1.jsp";
    }

    @Execute(validator = false)
    public String man() {
        BaridatorForm.answer[0] = "man";
        return "ask1.jsp";
    }

    @Execute(validator = false)
    public String glass() {
        BaridatorForm.answer[1] = "glass";
        return "ask2.jsp";
    }

    @Execute(validator = false)
    public String noglass() {
        BaridatorForm.answer[1] = "noglass";
        return "ask2.jsp";
    }

    @Execute(validator = false)
    public String freshman() {
        BaridatorForm.answer[2] = "freshman";
        return "ask3.jsp";
    }

    @Execute(validator = false)
    public String staff() {
        BaridatorForm.answer[2] = "staff";
        return "ask3.jsp";
    }

    @Execute(validator = false)
    public String testyes() {
        BaridatorForm.answer[3] = "yes";

//        List<Baridator> results = jdbcManager.from(Baridator.class).getResultList();

        return "result.jsp";
    }

    @Execute(validator = false)
    public String testno() {
        BaridatorForm.answer[3] = "no";

//        List<Baridator> results = jdbcManager.from(Baridator.class).getResultList();

        return "result.jsp";
    }
}