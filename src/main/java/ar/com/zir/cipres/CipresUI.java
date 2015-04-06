package ar.com.zir.cipres;

import ar.com.zir.skorpio.core.ui.SkorpioUI;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

@CDIUI
@Theme("cipres")
@SuppressWarnings("serial")
@PreserveOnRefresh
@Push(transport = Transport.STREAMING)
public class CipresUI extends SkorpioUI  {
    
    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
//        ((SkorpioConverterFactory)VaadinSession.getCurrent().getConverterFactory()).addConverter(new StringToPaisConverter());
        
        ConfirmDialog.Factory df = new DefaultConfirmDialogFactory() {

            @Override
            protected double[] getDialogDimensions(String message, ConfirmDialog.ContentMode style) {
                double[] dimensions = super.getDialogDimensions(message, style);
                dimensions[0] = dimensions[0] + 1;
                dimensions[1] = dimensions[1] + 1;
                return dimensions;
            }
            
        };
        ConfirmDialog.setFactory(df);
    }

}