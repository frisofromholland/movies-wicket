package nl.movie.web.component;

import nl.movie.service.MoviesRestClient;
import nl.movie.service.domain.Movie;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MoviesPanel extends Panel {

    @SpringBean
    private MoviesRestClient moviesRestClient;

    private IModel<Movie> selectedItem = new Model<>();
    private ScreeningsPanel screeningsPanel;
    private ModalWindow modalWindow;


    public MoviesPanel(String id, IModel<String> model) {
        super(id, model);
        screeningsPanel = new ScreeningsPanel("content", selectedItem);

        modalWindow = new ModalWindow("screeningsModal");
        modalWindow.setMaskType(ModalWindow.MaskType.SEMI_TRANSPARENT);
        modalWindow.setContent(screeningsPanel);
        modalWindow.setResizable(false);
        //modalWindow.setCssClassName("modal-dialog-custom");
        modalWindow.setTitle(new StringResourceModel("screeningsModalTitle"));
        add(modalWindow);

        List<IColumn<Movie, String>> columns = createColumns();


        final DataTable<Movie, String> dataTable = new DataTable<>("moviesTable", columns, new ListDataProvider<Movie>() {

            @Override
            protected List<Movie> getData() {
                return moviesRestClient.findByCity(getDefaultModelObjectAsString());
            }

        }, 1000);

        dataTable.addTopToolbar(new HeadersToolbar<>(dataTable, null));
        final Component moviesComponent = new WebMarkupContainer("moviesTable").add(dataTable);
        moviesComponent.setOutputMarkupId(true);

        add(moviesComponent);
    }

    private List<IColumn<Movie, String>> createColumns() {

        List<IColumn<Movie, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<>(new StringResourceModel("title"), "title"));
        columns.add(new PropertyColumn<>(new StringResourceModel("description"), "description"));
        //columns.add(new PropertyColumn<>(new StringResourceModel("duration"), "duration"));
        //columns.add(new PropertyColumn<>(new StringResourceModel("year"), "year"));
        columns.add(new PropertyColumn<>(new StringResourceModel("trailerLink"), "trailerLink"));
        columns.add(createDetailsLinkColumn());

        return columns;
    }


    protected final PropertyColumn<Movie, String> createDetailsLinkColumn() {
        return new PropertyColumn<Movie, String>(new StringResourceModel("screeningsLink"), "screeningsLink") {

            @Override
            public void populateItem(final Item item, final String componentId, final IModel rowModel) {

                item.add(new LabeledAjaxLink(componentId, Model.of("screeningsLink")) {
                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        selectedItem.setObject((Movie) rowModel.getObject());
                        modalWindow.show(target);
                    }


                });
                /*item.add(new AjaxLink<String>(componentId, new StringResourceModel("screeningsLink")) {
                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        selectedItem.setObject((Movie) rowModel.getObject());
                        modalWindow.show(target);
                    }
                });*/
            }
        };
    }

}

