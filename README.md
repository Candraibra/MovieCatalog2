# Submission 2: Aplikasi Movie Catalogue (UI/UX)


1. Terdapat 2 (Dua) Halaman Yang Menampilkan Daftar Film (Movies Dan Tv Show).

3. Menggunakan Fragment Untuk Menampung Halaman Movies Dan Tv Show.

5. Menggunakan Recyclerview Untuk Menampilkan Daftar Film Dengan Jumlah Minimal 10 Item.

7. Menggunakan Tablayout, BottomNavigationView, Atau Yang Lainnya Sebagai Navigasi Antara Halaman Movies Dan Tv Show.

9. Menampilkan Poster Dan Informasi Film Pada Halaman Detail Film.

11. Menggunakan Parcelable Sebagai Interfaces Dari Obyek Yang Akan Dikirimkan Antar Activity Atau Fragment.

13. Menggunakan Constraintlayout Untuk Menyusun Layout.

15. Aplikasi Harus Mendukung Bahasa Indonesia Dan Bahasa Inggris.

#### Kali Ini Saya Menggunakan BottomNavigationView dan Menggunakan Fragment Untuk Berpindah Darimenu Satu Ke Menu Lain
```java
private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    return true;

                case R.id.navigation_tv_show:
                    fragment = new TVFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();

                    return true;
            }
            return false;
        }
    };

```
#### Dan Ketika Nilai savedInstanceState == null, Maka Default Ke Menu Movie Use This
```java
        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_movies);
        }
```
#### Pada RecycleView Tidak Disediakan Onitemclick Maka Kita Harus Membuatnya Sendiri Dengan Membuat Kelas
```java
class ItemClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private ItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                if (mOnItemClickListener != null) {
                    view.setOnClickListener(mOnClickListener);
                }
                if (mOnItemLongClickListener != null) {
                    view.setOnLongClickListener(mOnLongClickListener);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
            }
        };
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }


    void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
```
#### Implementasikan Dengan Cara
```java
ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
```
#### Dan Saat Di Fragment Kita Tidak Bisa Menggunakan This Maka Ganti Dengan
```java
 adapter = new MovieAdapter(getActivity());
```
#### Oh Iya Satu Agi Untuk Menerapkan Parcalabe Ini Contohnya
```java
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie selectedMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        Toolbar myToolbar = findViewById(R.id.toolbar_top);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        if (selectedMovie != null) {
            ImageView imgPoster = findViewById(R.id.img_poster);
            imgPoster.setImageResource(selectedMovie.getPoster());
            TextView txtTitle = findViewById(R.id.txt_title);
            txtTitle.setText(selectedMovie.getTitle());
            TextView txtDescription = findViewById(R.id.txt_description);
            txtDescription.setText(selectedMovie.getDescription());

        }
        Tvshow selectedTv = getIntent().getParcelableExtra(EXTRA_TV);

        if (selectedTv != null) {
            ImageView imgPoster = findViewById(R.id.img_poster);
            imgPoster.setImageResource(selectedTv.getPoster());
            TextView txtTitle = findViewById(R.id.txt_title);
            txtTitle.setText(selectedTv.getTitle());
            TextView txtDescription = findViewById(R.id.txt_description);
            txtDescription.setText(selectedTv.getDescription());

        }

    }
}
```

> #####  Pengembangan Dari Submission 1 Dan Pada Project Ini Saya Menggunakan Recyclerview Tentunya Namun Masih Menggunakan Data Local(Drawable), Aplikasi Ini Support 2 Bahasa Yaitu Inggris Dan Indonesia -Pengembangan Dari Submission 1 Dan Pada Project Ini Saya Menggunakan Recyclerview Tentunya Namun Masih Menggunakan Data Local(Drawable), Aplikasi Ini Support 2 Bahasa Yaitu Inggris Dan Indonesia
