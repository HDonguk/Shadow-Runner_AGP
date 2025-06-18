package kr.ac.tukorea.ge.scgyong.cookierun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CookieAdapter extends RecyclerView.Adapter<CookieAdapter.CookieViewHolder> {

    private List<Cookie> cookieList;

    public CookieAdapter(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }

    @NonNull
    @Override
    public CookieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cookie, parent, false);
        return new CookieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookieViewHolder holder, int position) {
        Cookie cookie = cookieList.get(position);
        holder.cookieName.setText(cookie.name);
        holder.cookieImage.setImageResource(cookie.imageResId);
    }

    @Override
    public int getItemCount() {
        return cookieList.size();
    }

    public static class CookieViewHolder extends RecyclerView.ViewHolder {
        public TextView cookieName;
        public ImageView cookieImage;

        public CookieViewHolder(@NonNull View itemView) {
            super(itemView);
            cookieName = itemView.findViewById(R.id.cookieName);
            cookieImage = itemView.findViewById(R.id.cookieImage);
        }
    }
} 