package com.gwd.service.impl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gwd.Util.DateUtil;
import com.gwd.Util.MyFileUtil;
import com.gwd.dao.WishDao;
import com.gwd.entity.Wish;
import com.gwd.service.WishService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("wishService")
public class WishServiceImpl implements WishService {

    @Resource
    private WishDao wishDao;

    private static final int n = 10;


    @Override
    public List<Wish> getWishPool(Integer page) {
        return wishDao.selectPage(new Page<Wish>(page,n),new EntityWrapper<Wish>().eq("open",true).orderBy("create_time",false));
    }

    @Override
    public List<Wish> getWishDay(Integer page,String date) {
      //  String day = DateUtil.format.format(new Date()).split(" ")[0];
        List<Wish> wishList = wishDao.selectPage(new Page<Wish>(page,n),
                new EntityWrapper<Wish>().like("create_time",date).eq("open",true).orderBy("like_num",false)
        );
        return wishList;
    }

    @Override
    public List<Wish> getWishMonth(Integer page,String date) {
      //  String month = DateUtil.format.format(new Date()).substring(0,7);
      //  System.out.println(month);
        List<Wish> wishList = wishDao.selectPage(new Page<Wish>(page,n),
                new EntityWrapper<Wish>().eq("open",true).like("create_time",date).orderBy("like_num",false)
        );
        return wishList;
    }

    @Override
    public List<Wish> getWishAll(Integer page) {
        List<Wish> wishList = wishDao.selectPage(new Page<Wish>(page,n),
                new EntityWrapper<Wish>().eq("open",true).orderBy("like_num",false)
        );
        return wishList;
    }

    @Override
    public List<Wish> getRealWish(Integer page) {
        List<Wish> wishList = wishDao.selectPage(new Page<Wish>(page,n),
                new EntityWrapper<Wish>().eq("reality",true).orderBy("like_num",false)
        );
        return wishList;
    }


    @Override
    public void addWish(Integer userId,String content, MultipartFile image, boolean anonymous, boolean open) {
        /* public Wish(Integer userId, String content, String image, boolean anonymous, boolean open, String createTime, Integer likeNum)*/
        Wish wish;
        String createTime = DateUtil.format.format(new Date());
        if(image == null){
            wish = new Wish(userId,content,null,anonymous,open,createTime,0);
        }else {
            String imageName = null;
            try {
                 imageName = MyFileUtil.InImage(image,MyFileUtil.PATH_NUMBER_WISH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            wish = new Wish(userId,content,imageName,anonymous,open,createTime,0);
        }
       // System.out.println(wish);
        wishDao.insert(wish);
    }

    @Override
    public void updateAnonymousAndOpen(Integer id, Integer userId, boolean anonymous, boolean open) {
        wishDao.updateAnonymousAndOpen(id,userId,anonymous,open);
    }

    @Override
    public void deleteWish(Integer id, Integer userId) {
        wishDao.deleteWish(id,userId);
    }

    @Override
    public List<Wish> getUserAllWish(Integer userId, Integer page) {
        return wishDao.selectPage(new Page<Wish>(page,n),new EntityWrapper<Wish>().eq("user_id",userId).eq("open",true).eq("anonymous",false));
    }



}
