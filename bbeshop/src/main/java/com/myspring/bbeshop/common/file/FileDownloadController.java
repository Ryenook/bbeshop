package com.myspring.bbeshop.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	// 파일경로
	private static String CURR_IMAGE_REPO_PATH = "C:\\bbeshop\\file_repo";

//	@RequestMapping("/download.do")
//	protected void download(@RequestParam String imageFileName, @RequestParam String goods_id,
//			HttpServletResponse response) throws Exception {
//		// @RequestParam 사용하여 파일 이름을 바로 설정한다.
//		OutputStream out = response.getOutputStream();
//
//		String downFile = CURR_IMAGE_REPO_PATH + "\\" + goods_id + "\\" + imageFileName;
//		// 글번호와 파일 이름으로 다운로드할 파일 경로를 설정한다.
//
//		System.out.println("imageFileName = " + imageFileName);
//		File file = new File(downFile);
//		String enfileName = URLEncoder.encode(imageFileName, "utf-8");
//		// 한글 Filter를 걸었으나 해더(저장공간같은것)에는 적용이 안되어 받아온 파일이름이 한글일 경우
//		// 다운로드가 안되고 깨진 문구로 출력되어 다시 한번 encoding을 해줌
//
//		response.setHeader("Cache-Control", "no-cache");
//		response.addHeader("Content-disposition", "attachment; imageFileName=" + enfileName);
//		FileInputStream in = new FileInputStream(file);
//
//		byte[] buffer = new byte[1024 * 8];
//		while (true) {
//			int count = in.read(buffer); // 버퍼에 읽어들인 문자개수
//			if (count == -1) // 버퍼의 마지막에 도달했는지 체크
//				break;
//			out.write(buffer, 0, count);
//		}
//
//		in.close();
//		out.close();
//	}

//		업로드/인서트 된 파일 불러오기, goods_id와 fileName값을 받아 풀력한다.
		@RequestMapping("/download.do")
		protected void download(@RequestParam("fileName") String fileName,
			                 	@RequestParam("goods_id") String goods_id,
				                 HttpServletResponse response) throws Exception {
			OutputStream out = response.getOutputStream();
			String filePath=CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
			File image=new File(filePath);

			response.setHeader("Cache-Control","no-cache");
			response.addHeader("Content-disposition", "attachment; fileName="+fileName);
			FileInputStream in=new FileInputStream(image); 
			byte[] buffer=new byte[1024*8];
			while(true){
				int count=in.read(buffer); //버퍼에 읽어들인 문자개수
				if(count==-1)  //버퍼의 마지막에 도달했는지 체크
					break;
				out.write(buffer,0,count);
			}
			in.close();
			out.close();
		}

	// 업로드/인서트 된 파일 썸네일로 불러오기, goods_id와 fileName값을 받아 풀력한다.
	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("fileName") String fileName, @RequestParam("goods_id") String goods_id,
			HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + goods_id + "\\" + fileName;
		File image = new File(filePath);

		if (image.exists()) {
			Thumbnails.of(image).size(121, 154).outputFormat("png").toOutputStream(out);
		}

		// 파일크기 설정
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
}
