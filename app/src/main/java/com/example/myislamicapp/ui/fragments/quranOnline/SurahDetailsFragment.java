package com.example.myislamicapp.ui.fragments.quranOnline;

import static com.example.myislamicapp.data.utils.Constant.ABDEL_BASET;
import static com.example.myislamicapp.data.utils.Constant.ABDEL_BASET_MUGAWAD;
import static com.example.myislamicapp.data.utils.Constant.ABDEL_RASHEED;
import static com.example.myislamicapp.data.utils.Constant.ABDEL_WADOOD;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myislamicapp.R;
import com.example.myislamicapp.data.pojo.quranResponse.Surah;
import com.example.myislamicapp.data.pojo.translate.SurahDetail;
import com.example.myislamicapp.databinding.FragmentSurahDetailsBinding;
import com.example.myislamicapp.ui.adapters.SurahDetailsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SurahDetailsFragment extends Fragment {

    private FragmentSurahDetailsBinding binding;
    private QuranOnlineViewModel viewModel;
    private SurahDetailsAdapter adapter;
    private SurahDetailsFragmentArgs args;
    private List<SurahDetail> surahDetailList;
    private String englishLang = "english_hilali_khan";
    private Surah surah;
    private RadioGroup audioGroup;
    private RadioButton qariSelected;
    private String qariAB = "abdul_basit_murattal";
    private String qariAw = "abdul_wadood_haneef_rare";
    private String qariAR = "abdurrashid_sufi_shu3ba";
    private String abdulbaset_mujawwad = "abdulbaset_mujawwad";

    private String qari;
    private String str;
    int soraNo = 0;

    Handler handler = new Handler();
    MediaPlayer mediaPlayer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = SurahDetailsFragmentArgs.fromBundle(requireArguments());
        surah = args.getSurah();
        adapter = new SurahDetailsAdapter(new ArrayList<>());
        viewModel = new ViewModelProvider(this).get(QuranOnlineViewModel.class);
        surahDetailList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        soraNo = args.getSurah().getNumber();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSurahDetailsBinding.inflate(inflater);

        searchAboutAya();
        return binding.getRoot();
    }

    private void searchAboutAya() {
        binding.detailEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String id) {
        ArrayList<SurahDetail> surahDetails = new ArrayList<>();
        for (SurahDetail detail : surahDetailList) {
            if (String.valueOf(detail.getAya()).contains(id)) {
                surahDetails.add(detail);
            }
        }
        adapter.setSoraDetail(surahDetails);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpQari();
        setupData();
        setRecyclerView();
        surahTranslation(englishLang, surah.getNumber());
        listenAudio(qariAB);


    }

    @SuppressLint("ClickableViewAccessibility")
    private void listenAudio(String qari) {

        mediaPlayer = new MediaPlayer();
        binding.qariSeekbar.setMax(100);

        binding.imagePlay.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                binding.imagePlay.setImageResource(R.drawable.play);
            } else {
                mediaPlayer.start();
                binding.imagePlay.setImageResource(R.drawable.image_pause);
                updateSeekBar();
            }
        });
        prepareMediaPlayer(qari);
        binding.qariSeekbar.setOnTouchListener((view, motionEvent) -> {
            SeekBar seekBar = (SeekBar) view;
            int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
            mediaPlayer.seekTo(playPosition);
            binding.textStartTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
            return false;
        });
        mediaPlayer.setOnBufferingUpdateListener((mediaPlayer, position) -> {
            binding.qariSeekbar.setSecondaryProgress(position);
        });
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            binding.qariSeekbar.setProgress(0);
            binding.imagePlay.setImageResource(R.drawable.play);
            binding.textStartTime.setText("0:00");
            binding.textTotalTime.setText("0:00");
            mediaPlayer.reset();
            prepareMediaPlayer(qari);
        });
    }

    private void prepareMediaPlayer(String qari) {

        if (soraNo < 10) {
            str = "00" + soraNo;
        } else if (soraNo < 100) {
            str = "0" + soraNo;
        } else {
            str = String.valueOf(soraNo);
        }

        try {
            mediaPlayer.setDataSource("https://download.quranicaudio.com/quran/" + qari + "/" + str.trim() + ".mp3");
            mediaPlayer.prepare();
            binding.textTotalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Runnable updater = () -> {
        updateSeekBar();
        long currentDuration = mediaPlayer.getCurrentPosition();
        binding.textStartTime.setText(timeToMilliSecond(currentDuration));
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            binding.qariSeekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }


    private void setUpQari() {
        binding.icSelectQari.setOnClickListener(v -> {
            BottomSheetDialog sheet = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.item_bottom_sheet_qaree,
                    v.findViewById(R.id.sheetContainer));

            view.findViewById(R.id.btnSaveQaree).setOnClickListener(view1 -> {
                audioGroup = view.findViewById(R.id.radioGroup);
                int audioId = audioGroup.getCheckedRadioButtonId();
                qariSelected = view.findViewById(audioId);

                if (qariSelected.getText().toString().equals(ABDEL_BASET)) {
                    qari = qariAB;
                } else if (qariSelected.getText().toString().equals(ABDEL_WADOOD)) {
                    qari = qariAw;
                } else if (qariSelected.getText().toString().equals(ABDEL_RASHEED)) {
                    qari = qariAR;
                } else if (qariSelected.getText().toString().equals(ABDEL_BASET_MUGAWAD)) {
                    qari = abdulbaset_mujawwad;
                }

                mediaPlayer.reset();
                mediaPlayer.release();
                listenAudio(qari);
                sheet.dismiss();

            });
            sheet.setContentView(view);
            sheet.show();

        });
    }

    private void surahTranslation(String englishLang, int number) {
        if (surahDetailList.size() > 0) {
            surahDetailList.clear();
        }
        viewModel.getSurahDetails(englishLang, number).observe(getViewLifecycleOwner(), surahDetailsResponse -> {
            for (int i = 0; i < surahDetailsResponse.getSurahDetailList().size(); i++) {
                surahDetailList.add(new SurahDetail(
                                surahDetailsResponse.getSurahDetailList().get(i).getId(),
                                surahDetailsResponse.getSurahDetailList().get(i).getSura(),
                                surahDetailsResponse.getSurahDetailList().get(i).getAya(),
                                surahDetailsResponse.getSurahDetailList().get(i).getArabicText(),
                                surahDetailsResponse.getSurahDetailList().get(i).getTranslation(),
                                surahDetailsResponse.getSurahDetailList().get(i).getFootnotes()
                        )

                );
                adapter.setSoraDetail(surahDetailList);
                binding.soraDetailsRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void setRecyclerView() {
        binding.soraDetailsRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.soraDetailsRv.setHasFixedSize(true);

    }

    private void setupData() {
        binding.detailsSoraEnName.setText(surah.getEnglishName());
        binding.detailSoraArName.setText(surah.getName());
        binding.detailSoraAyatNo.setText("عدد اياتها : " + surah.getNumberOfAyahs());
        if (Objects.equals(surah.getRevelationType(), "Meccan")) {
            binding.detailSoraType.setText("مكية");
        } else {
            binding.detailSoraType.setText("مدنية");
        }

    }

    public String timeToMilliSecond(long time) {
        String timerString = "";
        String secondString = "";

        int hour = (int) (time / (1000 * 60 * 60));
        int minutes = (int) (time % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((time % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hour > 0) {
            timerString = hour + ":";
        }
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            binding.imagePlay.setImageResource(R.drawable.play);
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            binding.imagePlay.setImageResource(R.drawable.play);
        }
        super.onStop();
    }

    @Override
    public void onPause() {
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            binding.imagePlay.setImageResource(R.drawable.play);
        }
        super.onPause();
    }
}