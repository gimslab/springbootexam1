package readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {

	@Autowired
	private ReadingListRepository readingListRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("reader", reader);
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
}
