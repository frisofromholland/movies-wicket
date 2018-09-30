package nl.movie.web.component;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 *
 */
public abstract class LabeledAjaxLink extends AjaxLink<String> {

    public LabeledAjaxLink(String id, IModel<String> model) {
        super(id);
    }

}