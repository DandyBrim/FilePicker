package com.imlibo.filepicker.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.imlibo.filepicker.model.EssFile;
import com.imlibo.filepicker.model.FileEvent;
import com.imlibo.filepicker.model.GlideApp;
import com.imlibo.filepicker.util.FileSizeUtil;
import com.imlibo.filepicker.util.FileUtils;
import com.imlibo.filepicker.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * FileListAdapter
 * Created by 李波 on 2018/2/3.
 */

public class FileListAdapter extends BaseQuickAdapter<EssFile, BaseViewHolder> {
    public FileListAdapter(@Nullable List<EssFile> data) {
        super(R.layout.item_file_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EssFile item) {
        TextView textView = helper.getView(R.id.tv_item_file_list_desc);
        if (item.isDirectory()) {
            helper.setVisible(R.id.iv_item_file_select_right, true);
            if(item.getChildFolderCount().equals("加载中")){
                //查找数量
                EventBus.getDefault().post(new FileEvent(helper.getAdapterPosition()));
            }
            textView.setText(String.format(mContext.getString(R.string.folder_desc), item.getChildFileCount(),item.getChildFolderCount()));
        } else {
            helper.setVisible(R.id.iv_item_file_select_right, false);
            textView.setText(String.format(mContext.getString(R.string.file_desc), FileUtils.getDateTime(item.getAbsolutePath()), FileSizeUtil.getAutoFileOrFilesSize(item.getFile())));
        }
        helper.setText(R.id.tv_item_file_list, item.getName());
        if(item.isChecked()){
            helper.setVisible(R.id.checkbox_item_file_list,true);
        }else {
            helper.setVisible(R.id.checkbox_item_file_list,false);
        }
        ImageView imageView = helper.getView(R.id.iv_item_file_select_left);
        String fileNameExtension = FileUtils.getExtension(item.getName()).toLowerCase();
        switch (fileNameExtension) {
            case "apk":
                imageView.setImageResource(R.mipmap.apk);
                break;
            case "avi":
                imageView.setImageResource(R.mipmap.avi);
                break;
            case "doc":
            case "docx":
                imageView.setImageResource(R.mipmap.doc);
                break;
            case "exe":
                imageView.setImageResource(R.mipmap.exe);
                break;
            case "flv":
                imageView.setImageResource(R.mipmap.flv);
                break;
            case "gif":
                GlideApp
                        .with(mContext)
                        .load(item.getAbsolutePath())
                        .placeholder(R.mipmap.gif)
                        .centerCrop()
                        .into(imageView);
                break;
            case "jpg":
            case "jpeg":
            case "png":
                GlideApp
                        .with(mContext)
                        .load(item.getAbsolutePath())
                        .placeholder(R.mipmap.png)
                        .centerCrop()
                        .into(imageView);
                break;
            case "mp3":
                imageView.setImageResource(R.mipmap.mp3);
                break;
            case "mp4":
            case "f4v":
                imageView.setImageResource(R.mipmap.movie);
                break;
            case "pdf":
                imageView.setImageResource(R.mipmap.pdf);
                break;
            case "ppt":
            case "pptx":
                imageView.setImageResource(R.mipmap.ppt);
                break;
            case "wav":
                imageView.setImageResource(R.mipmap.wav);
                break;
            case "xls":
            case "xlsx":
                imageView.setImageResource(R.mipmap.xls);
                break;
            case "zip":
                imageView.setImageResource(R.mipmap.zip);
                break;
            case "ext":
            default:
                if (item.isDirectory()) {
                    imageView.setImageResource(R.mipmap.folder);
                } else {
                    imageView.setImageResource(R.mipmap.documents);
                }
                break;
        }
    }
}
