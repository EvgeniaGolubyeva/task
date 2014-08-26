package mms.controller;

import mms.db.MessageDao;
import mms.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    public static final String CREATE_EDIT_VIEW = "createedit";
    public static final String MAIN_VIEW = "main";

    public static final String HOME_REDIRECT = "redirect:/";
    public static final String MODEL_NAME = "model";

    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value={"/messages", "/"}, method = RequestMethod.GET)
    @Transactional
    public ModelAndView getMessages(Model model) {
        model.addAttribute("messages", messageDao.fetchAll());

        return new ModelAndView(MAIN_VIEW, MODEL_NAME, model);
    }

    @RequestMapping(value="/message/{id}", method = RequestMethod.GET)
    @Transactional
    public ModelAndView getMessage(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", messageDao.fetch(id));

        return new ModelAndView(CREATE_EDIT_VIEW, MODEL_NAME, model);
    }

    @RequestMapping(value="/message/new", method = RequestMethod.GET)
    @Transactional
    public ModelAndView createMessage(Model model) {
        model.addAttribute("message", new Message());

        return new ModelAndView(CREATE_EDIT_VIEW, MODEL_NAME, model);
    }

    @RequestMapping(value="/message/{id}/delete", method = RequestMethod.GET)
    @Transactional
    public String deleteMessage(@PathVariable("id") long id) {
        messageDao.delete(id);

        return HOME_REDIRECT;
    }

    @RequestMapping(value="/message/update", method = RequestMethod.POST, params = "save")
    @Transactional
    public String saveMessageUpdate(Message message) {
        messageDao.saveOrUpdate(message);

        return HOME_REDIRECT;
    }

    @RequestMapping(value="/message/update", method = RequestMethod.POST, params = "cancel")
    public String cancelMessageUpdate() {
        return HOME_REDIRECT;
    }
}
