package tutorial.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class BaridatorForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public String[] answerform = new String[4];

    public void initialized() {
        for (int i = 0; i < answerform.length; i++){
            answerform[i] = "";
        }
    }
}