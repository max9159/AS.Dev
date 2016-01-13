package lib_self;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by Max on 2016/1/13.
 */
public class UniversalImageLoaderHelper {
    private static ImageLoader imageLoader;
    public static ImageLoader getInstance(Context context){
        if(imageLoader==null){
            imageLoader=ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext()).defaultDisplayImageOptions(options).build();
            imageLoader.init(config);
        }
        return imageLoader;
    }
}
