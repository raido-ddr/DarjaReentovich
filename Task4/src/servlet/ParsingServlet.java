package servlet;

import com.raido.task4.entity.MobileTariff;
import com.raido.task4.builder.TariffBuilder;
import com.raido.task4.builderfactory.TariffBuilderFactory;
import com.raido.task4.exception.LogicalException;
import com.raido.task4.exception.TechnicalException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/parseaction")
public class ParsingServlet extends HttpServlet {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ParsingServlet.class);
        new DOMConfigurator().doConfigure("log4j.xml",
                LogManager.getLoggerRepository());
        LOGGER.setLevel(Level.INFO);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       try {
            String parserType = request.getParameter("parserType").toUpperCase();

            TariffBuilderFactory factory = new TariffBuilderFactory();

            TariffBuilder builder = factory.createTariffBuilder(parserType);

            String fileName = request.getParameter("sourceFile");
            String filePath = getServletContext().getRealPath("resources/" + fileName);

            builder.buildTariffsSet(filePath);
            Set<MobileTariff> tariffSet = builder.getMobileTariffs();

            request.setAttribute("tariffSet", tariffSet);
            request.setAttribute("parserType", parserType);
            request.getRequestDispatcher("jsp/result.jsp").forward(request, response);

        } catch (TechnicalException | LogicalException e) {
            LOGGER.error(e);
            request.setAttribute("exceptionMessage", e.getMessage());
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
