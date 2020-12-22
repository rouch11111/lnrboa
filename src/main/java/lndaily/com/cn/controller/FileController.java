package lndaily.com.cn.controller;

import lndaily.com.cn.bean.Image;
import lndaily.com.cn.bean.User;
import lndaily.com.cn.json.ImageUpload;
import lndaily.com.cn.json.MultiFileUpload;
import lndaily.com.cn.json.Table;
import lndaily.com.cn.service.FileService;
import lndaily.com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Value("${web.imagepath}")
    private String imagepath;
    @Value("${web.filepath}")
    private String filepath;
    @Value("${web.uploadpath}")
    private String uploadpath;

    private boolean saveFile(MultipartFile file, String path) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                String savePath = path + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //文件上传
    @RequestMapping("upload")
    public MultiFileUpload multiFileUpload(MultipartFile file,String range){
        MultiFileUpload multiFileUpload = new MultiFileUpload();
        multiFileUpload.setCode(0);
        multiFileUpload.setMsg("success");
        List<String> filelist = new ArrayList<>();
        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyyMMdd").format(date);
        //String path = "E://file//"+dataForm+"//";
        String path = filepath+dataForm+"//";
        boolean saveFile = saveFile(file, path);
        filelist.add(path+file.getOriginalFilename());
        filelist.add(file.getOriginalFilename());
        multiFileUpload.setData(filelist);
        fileService.savefile(path+file.getOriginalFilename(),file.getOriginalFilename(),range);

        return multiFileUpload;
    }
    //附件上传
    @RequestMapping("fujian")
    public MultiFileUpload fujian(MultipartFile file){
        MultiFileUpload multiFileUpload = new MultiFileUpload();
        multiFileUpload.setCode(0);
        multiFileUpload.setMsg("success");
        List<String> filelist = new ArrayList<>();
        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyyMMdd").format(date);
        //String path = "E://upload//"+dataForm+"//";
        String path = uploadpath+dataForm+"//";
        boolean saveFile = saveFile(file, path);
        filelist.add(path+file.getOriginalFilename());
        filelist.add(file.getOriginalFilename());
        multiFileUpload.setData(filelist);

        return multiFileUpload;
    }

    //layui编辑器的图片上传api，暂时不用
    @RequestMapping("image")
    public ImageUpload imageUpload(MultipartFile file) throws FileNotFoundException {

        String url = "";
        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyyMMdd").format(date);
        url = request.getScheme() +"://" + request.getServerName()
                +":" +request.getServerPort()+"/jsb/img/"+dataForm+"/";

        //System.out.println(dataForm);
        String path= ResourceUtils.getURL("classpath:").getPath()+"/static/img/"+dataForm+"/";
        boolean saveFile = saveFile(file, path);
        //System.out.println(path);
        String filepath = path + file.getOriginalFilename();
        Image image = new Image();
        image.setSrc(url + file.getOriginalFilename());

        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setCode(0);
        imageUpload.setMsg("success");
        imageUpload.setData(image);
        return imageUpload;
    }
    //编辑器图片上传
    @RequestMapping("newimage")
    public Map<String,Object> newimage(MultipartFile file) throws FileNotFoundException {

        String url = "";
        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyyMMdd").format(date);
        url = request.getScheme() +"://" + request.getServerName()
                +":" +request.getServerPort()+"/lnrb/image/"+dataForm+"/";
        //String path= ResourceUtils.getURL("classpath:").getPath()+"/static/img/"+dataForm+"/";
        String path = imagepath+"/"+dataForm+"/";
        boolean saveFile = saveFile(file, path);
        String urlname = url + file.getOriginalFilename();

        Map map = new HashMap<>();
        map.put("location",urlname);

        return map;
    }
    //文件下载
    @RequestMapping("download")
    public void download(String workfile,String workfilename ,HttpServletResponse response) throws Exception{
        File file = new File(workfile);
        if(!file.exists()){
            System.out.println("no exist");
            return;
        }
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(workfilename, "UTF-8"));
        OutputStream out = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);

            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1){
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                out.write(buffer,0,b);
            }
            inputStream.close();
            out.close();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("filelist")
    public Table<lndaily.com.cn.bean.File> filelist(int page, int limit){
        
        return fileService.findall(page,limit);
    }
    @RequestMapping("findcon")
    public Table<lndaily.com.cn.bean.File> findcon(lndaily.com.cn.bean.File file,String dept,String group){
        if("on".equals(group)){
            file.setIsgroup((short) 1);
        }
        if("on".equals(dept)){
            Integer userid = (Integer) session.getAttribute("userid");
            User user = userService.findById(userid);
            Integer deptid = user.getDeptid();
            file.setDept_id(deptid);
        }

        return fileService.findcon(file);
    }

    @RequestMapping("delfile")
    public String delfile(lndaily.com.cn.bean.File file){
        return fileService.delfile(file);
    }
}
