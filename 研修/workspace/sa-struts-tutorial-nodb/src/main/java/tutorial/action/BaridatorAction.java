package tutorial.action;

import java.util.HashMap;

//import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.extension.jdbc.JdbcManager;
//import org.seasar.framework.container.SingletonS2Container;
//import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

//import tutorial.entity.Baridator;
import tutorial.form.BaridatorForm;



public class BaridatorAction {

    public JdbcManager jdbcManager;
    public String searchname = "lol";
    public String[] answer = new String[4];
    public HttpServletRequest req;
    public HttpSession session;

    public HashMap<String, String> askmap = new HashMap<String, String>();

    @ActionForm
    @Resource
    BaridatorForm BaridatorForm;

    @Execute(validator = false)
    public String index() {
        BaridatorForm.initialized();
        session.removeAttribute("question1");
        session.removeAttribute("question2");
        session.removeAttribute("question3");
        session.removeAttribute("question4");
        return "index.jsp";
    }

    @Execute(validator = false)
    public String question() {
        session = req.getSession();
        return "ask0.jsp";
    }

    @Execute(validator = false)
    public String woman() {
        session.setAttribute("question1", "woman");
        return "ask1.jsp";
    }

    @Execute(validator = false)
    public String man() {
        session.setAttribute("question1", "man");
        return "ask1.jsp";
    }

    @Execute(validator = false)
    public String glass() {
        session.setAttribute("question2", "glass");
        return "ask2.jsp";
    }

    @Execute(validator = false)
    public String noglass() {
        session.setAttribute("question2", "noglass");
        return "ask2.jsp";
    }

    @Execute(validator = false)
    public String freshman() {
        session.setAttribute("question3", "freshman");
        return "ask3.jsp";
    }

    @Execute(validator = false)
    public String staff() {
        session.setAttribute("question3", "staff");
        return "ask3.jsp";
    }

    @Execute(validator = false)
    public String testyes() {
        session.setAttribute("question4", "yes");
        getUser();

        return "result.jsp";
    }

    @Execute(validator = false)
    public String testno() {
        session.setAttribute("question4", "no");
        getUser();

        return "result.jsp";
    }

    public void getUser() {

        if (doQuestion1() == true && doQuestion2() == true && doQuestion3()
                == true && doQuestion4() == true) searchname = "man&glass&staff&yes";
        if (doQuestion1() == true && doQuestion2() == true && doQuestion3()
                == true && doQuestion4() == false) searchname = "man&glass&staff&no";
        if (doQuestion1() == true && doQuestion2() == true && doQuestion3()
                == false && doQuestion4() == true) searchname = "man&glass&freshman&yes";
        if (doQuestion1() == true && doQuestion2() == true && doQuestion3()
                == false && doQuestion4() == false) searchname = "man&glass&freshman&no";
        if (doQuestion1() == true && doQuestion2() == false && doQuestion3()
                == true && doQuestion4() == true) searchname = "man&noglass&staff&yes";
        if (doQuestion1() == true && doQuestion2() == false && doQuestion3()
                == true && doQuestion4() == false) searchname = "man&noglass&staff&no";
        if (doQuestion1() == true && doQuestion2() == false && doQuestion3()
                == false && doQuestion4() == true) searchname = "man&noglass&freshman&yes";
        if (doQuestion1() == true && doQuestion2() == false && doQuestion3()
                == false && doQuestion4() == false) searchname = "man&noglass&freshman&no";

        if (doQuestion1() == false && doQuestion2() == true && doQuestion3()
                == true && doQuestion4() == true) searchname = "woman&glass&staff&yes";
        if (doQuestion1() == false && doQuestion2() == true && doQuestion3()
                == true && doQuestion4() == false) searchname = "woman&glass&staff&no";
        if (doQuestion1() == false && doQuestion2() == true && doQuestion3()
                == false && doQuestion4() == true) searchname = "woman&glass&freshman&yes";
        if (doQuestion1() == false && doQuestion2() == true && doQuestion3()
                == false && doQuestion4() == false) searchname = "woman&glass&freshman&no";
        if (doQuestion1() == false && doQuestion2() == false && doQuestion3()
                == true && doQuestion4() == true) searchname = "woman&noglass&staff&yes";
        if (doQuestion1() == false && doQuestion2() == false && doQuestion3()
                == true && doQuestion4() == false) searchname = "woman&noglass&staff&no";
        if (doQuestion1() == false && doQuestion2() == false && doQuestion3()
                == false && doQuestion4() == true) searchname = "woman&noglass&freshman&yes";
        if (doQuestion1() == false && doQuestion2() == false && doQuestion3()
                == false && doQuestion4() == false) searchname = "woman&noglass&freshman&no";

    }

    public boolean doQuestion1() {
        String answer = (String) session.getAttribute("question1");

        if (answer.equals("man")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doQuestion2() {
        String answer = (String) session.getAttribute("question2");

        if (answer.equals("glass")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doQuestion3() {
        String answer = (String) session.getAttribute("question3");

        if (answer.equals("staff")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doQuestion4() {
        String answer = (String) session.getAttribute("question4");

        if (answer.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }

}