package com.und3f1n3d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.und3f1n3d.fragments.NoteEditFragment;
import com.und3f1n3d.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MenuItem addNote;
    private MenuItem deleteNote;
    private Menu mainMenu;
    private List<Note> notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            notes.add(Note.makeNewNote("xyi" + i));
            System.out.println(notes.get(i));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        mainMenu = menu;
        deleteNote = menu.findItem(R.id.deleteNote);
        addNote = menu.findItem(R.id.addNote);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNote:
                FragmentTransaction transaction1 = getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("notesListFragment")
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction1.replace(R.id.mainLayout, new NoteEditFragment());
                transaction1.commit();
                addNote.setVisible(false);
                deleteNote.setVisible(true);
                return true;
            case  R.id.deleteNote:
                onBackPressed();
                addNote.setVisible(true);
                deleteNote.setVisible(false);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
        if(fragmentCount == 0){
            super.onBackPressed();
        }else{
            addNote.setVisible(true);
            deleteNote.setVisible(false);
            getSupportFragmentManager().popBackStack();
        }
    }
}
