package com.colloboration.chatapp.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.colloboration.chatapp.model.ChatUser;
import com.colloboration.chatapp.model.admin;
import com.colloboration.chatapp.service.ChatuserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
// @RequestMapping("/user")
public class HomeController {

	@Autowired
	ChatuserService cs;
	@Autowired
	SessionFactory sf;
	@Autowired
	HttpSession ses;
	@Autowired
	HttpServletRequest req;
	String otp = null;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registeruser(HttpServletRequest req) {
		System.out.println("in controller-reg");
		return new ModelAndView("chatuserregister");
	}

	// creating obj for spring form usage
	@ModelAttribute("cht")
	public ChatUser getUser() {
		return new ChatUser();
	}

	@Autowired
	private JavaMailSender crunchifymail;
	String fileName = null;

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public ModelAndView registeruser1(@Valid HttpServletRequest req, @ModelAttribute("Chatuser") final ChatUser usr,
			BindingResult result) {
		String npath = null;
		System.out.println("in controller-reg - post");
		ModelAndView mv = new ModelAndView("chatuserregister");
		String ret = null;
		ServletContext sctx = req.getServletContext();
		String p = sctx.getRealPath("/");
		MultipartFile file1 = usr.getFile1();
		if (result.hasErrors()) {
			System.out.println("br " + result.getErrorCount());
		} else {
			System.out.println("nno err");
		}

		if (!usr.getFile1().isEmpty()) {
			try {

				fileName = file1.getOriginalFilename();
				byte[] bytes = file1.getBytes();
				npath = p + "\\resources\\images\\" + fileName;
				System.out.println(npath);
				// String npath="/App/reources/"+fileName;
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(npath)));
				buffStream.write(bytes);
				buffStream.close();
				String dbfilename = "/resources/images/" + fileName;
				usr.setUserId(genadminrandomid());
				usr.setFpath(dbfilename);
				// generates OTP and stores in global variable -> otp
				generateOTP();
				usr.setQrotp(otp);
				usr.setEnabled(false);
				// usr.setFriendId("s1");
				/*
				 * authorize data added to db
				 */
				admin ad = new admin();
				ad.setId_fk(usr);
				System.out.println(usr.getFpath() + usr.getMobnum() + usr.getUserId());
				cs.addProd(usr);
				ad.setRolename("ROLE_USER");
				sf.openSession().save(ad);

				crunchifymail.send((new MimeMessagePreparator() {

					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						messageHelper.setTo(usr.getMailId());
						messageHelper.setSubject("Thanks for registering with us!!!");
						messageHelper.setText("use this OTP code : " + otp);

						// determines if there is an upload file, attach it to
						// the e-mail
						String attachName = usr.getFile1().getOriginalFilename();
						if (!usr.getFile1().equals("")) {

							messageHelper.addAttachment(attachName, new InputStreamSource() {

								@Override
								public InputStream getInputStream() throws IOException {
									return usr.getFile1().getInputStream();
								}
							});
						}

					}

				}));

				// with out attachment
				// SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
				// crunchifyMsg.setFrom("test28780@gmail.com");
				// crunchifyMsg.setTo(usr.getMailId());
				// crunchifyMsg.setSubject("Thanks for registering");
				// crunchifyMsg.setText("thanks for registering with us...");
				// crunchifymail.send(crunchifyMsg);
				String qrpagelink = "<a href='qrpage'>click to get qrcode</a>' ";
				ret = "mail Sent successfully!!! please check for activation" + qrpagelink;
				genQrcode(npath);
				mv.addObject("msg", ret);
			} catch (Exception e) {
				ret = "user & img uploaded falied!!!";
				mv.addObject("msg", ret);
				System.out.println(e + "You failed to upload " + fileName + ": " + e.getMessage());
			}
		} else {
			ret = "user & img uploaded falied!!!";
			mv.addObject("msg", ret);
		}
		return mv;
	}

	/*
	 * to generate id for user
	 */
	public String genadminrandomid() {
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order
		LinkedHashSet<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < 10000) {
			Integer next = rng.nextInt(20000) + 1;
			// As we're adding to a set, this will automatically do a
			// containment check
			generated.add(next);
		}
		System.out.println("in gen random pwd " + generated.iterator().next());
		return "C" + generated.iterator().next();

	}

	/*
	 * 
	 * to retrieve and display details of friwnds for particular user
	 */
	public ModelAndView getFriendsById(String fid) {
		return new ModelAndView();
	}

	@RequestMapping("/getAlluser")
	public @ResponseBody List<ChatUser> getAllFriends() {
		List<ChatUser> l = cs.getAllUsers();
		return l;
	}

	@RequestMapping("/getuser")
	public String getuser() {
		return "displayUsers";
	}

	@RequestMapping(value = { "/login", "/userLogin" }, method = RequestMethod.GET)
	public String getlogin() {
		return "userLogin";
	}

	@RequestMapping(value = { "/login", "/userLogin" }, method = RequestMethod.POST)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String ret = "";
		System.out.println("in admin login" + error);
		if (error != null) {
			ret = "error";
			System.out.println("errrr");
			model.addAttribute("error", "Invalid username and password!");
		} else if (error == null) {
			System.out.println("succ");
			ret = "index";
			model.addAttribute("error", "Success!");
		}
		if (logout != null) {

			model.addAttribute("error", "You have been logged out successfully.");
		}

		return ret;
	}

	@RequestMapping("/err")
	public String suc() {
		return "err";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "/index";// You can redirect wherever you want, but generally
						// it's a good practice to show login screen again.
	}

	public void genQrcode(String npath) {

		String myCodeText = otp;
		
		int size = 250;
		String fileType = "png";
		fileName="qrimg.png";
		ServletContext sctx = req.getServletContext();
		String p = sctx.getRealPath("/");
		npath=p + "\\resources\\images\\qrcode\\"+fileName; 
		File myFile = new File(npath);
		try {

			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			// Now with zxing version 3.2.1 you could change border size (white
			// border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			System.out.println("-p-p- "+npath);
			ImageIO.write(image, fileType, myFile);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nYou have successfully created QR Code.");
	}

	/* generate OTP code for registered users */
	public void generateOTP() {
		Random r = new Random();
		otp = new String();
		for (int i = 0; i < 8; i++) {
			otp += r.nextInt(10);
		}
		System.out.println(otp);

	}

	@RequestMapping("/qrpage")
	public ModelAndView qrpage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("qr", fileName);
		return mv;
	}

	@RequestMapping("/qrverify")
	public ModelAndView qrverifyforlogin(@RequestParam("mailid") String mailid,@RequestParam("otp") String otp) {
		boolean b=cs.verifyOtp(mailid,otp);
		ModelAndView mv = new ModelAndView("qrpage");
		mv.addObject("ver",b);
		return mv;
	}

}
