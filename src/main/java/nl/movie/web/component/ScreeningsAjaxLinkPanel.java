package nl.movie.web.component;

import nl.movie.service.domain.Movie;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

/**
 *
 */
public class ScreeningsAjaxLinkPanel extends Panel {

    public ScreeningsAjaxLinkPanel(String id, IModel<Movie> selectItemModel, final ModalWindow modalWindow, final IModel rowModel) {
        super(id);

        AjaxLink<String> ajaxLink = new AjaxLink<String>("link") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                selectItemModel.setObject((Movie) rowModel.getObject());
                modalWindow.show(target);
            }
        };

        ajaxLink.add(new Label("label", new StringResourceModel("label")));
        add(ajaxLink);

    }

}