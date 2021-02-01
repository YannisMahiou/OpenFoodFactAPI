import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class using mock MVC test util
 */
public class TestProductController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetBarcodePrince() throws Exception {
        this.mockMvc.perform(get("/product/barcode/7622210449283")).andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.name").value("BISCUITS FOURRÉS (35%) PARFUM CHOCOLAT - Prince"));
    }

    @Test
    void testBarcodeNotFound() throws Exception {
        this.mockMvc.perform(get("/product/barcode/666")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void test4XXClientError() throws Exception {
        this.mockMvc.perform(get("/product/barcode/")).andDo(print()).andExpect(status().is4xxClientError());
    }

}
