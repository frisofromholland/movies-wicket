package nl.movie.web.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

/**
 *
 */
public class HeaderPanel extends Panel {

    public HeaderPanel(final String id) {
        super(id);
        add(new Label("title", new StringResourceModel("title")));
    }
}