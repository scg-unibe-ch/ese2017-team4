package tests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ese4.Application.class)
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHomescreen() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Tourverwalter")));
    }
    
    @Test
    public void shouldReturnaddPackageForm() throws Exception {
        this.mockMvc.perform(get("/package/addPackageForm")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Paket hinzufügen")));
    }
    
    @Test
    public void shouldReturnlistAllPackagesForm() throws Exception {
        this.mockMvc.perform(get("/package/listAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Liste aller Pakete")));
    }
    
    @Test
    public void shouldReturnaddUserForm() throws Exception {
        this.mockMvc.perform(get("/user/addUserForm")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Erfasse einen neuen Benutzer")));
    }
    
    @Test
    public void shouldReturnlistAllUserForm() throws Exception {
        this.mockMvc.perform(get("/user/listAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Liste aller Benutzer")));
    }
    
    @Test
    public void shouldReturnpackageSelectionForm() throws Exception {
        this.mockMvc.perform(get("/tour/makeTour")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Paketauswahl")));
    }
    
    @Test
    public void shouldReturnlistAllToursForm() throws Exception {
        this.mockMvc.perform(get("/tour/listAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Liste aller Touren")));
    }
}
