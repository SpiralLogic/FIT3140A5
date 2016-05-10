package com.srjengbro.scratchbasic;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.srjengbro.scratchbasic.instructions.GotoInstruction;
import com.srjengbro.scratchbasic.instructions.IfInstruction;
import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;

import java.util.ArrayList;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Adapter for providing the list of instructions to the list view
 */
public class InstructionAdapter extends BaseAdapter implements ListAdapter {

    /**
     * layout inflater
     */
    private LayoutInflater inflater;
    /**
     * instruction list
     */
    private ArrayList<Instruction> instructionList;
    /**
     * contxt activity the adapter is for
     */
    private Context context;


    /**
     * @param instructionList instuction list
     * @param ctx context
     */
    public InstructionAdapter(ArrayList<Instruction> instructionList, Context ctx) {
        super();
        inflater = LayoutInflater.from(ctx);
        this.instructionList = instructionList;
        this.context = ctx;

    }

    /**
     * @return returns the count of the instructions
     */
    @Override
    public int getCount() {
        return instructionList.size();
    }

    /**
     * @param position gets a specific instruction
     * @return the position of the instruction
     */
    @Override
    public Object getItem(int position) {
        return instructionList.get(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Creates the layout from the instructionText line template.
     * There will be no remove button if it is the first line so that the first line cant be removed
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.instruction_line, parent, false);
            holder = new ViewHolder();
            holder.instructionText = (TextView) convertView.findViewById(R.id.instruction);
            holder.commandList = (Spinner) convertView.findViewById(R.id.spn_command);
            holder.editButton = (Button) convertView.findViewById(R.id.edit_button);
            holder.addButton = (Button) convertView.findViewById(R.id.add_button);
            holder.removeButton = (Button) convertView.findViewById(R.id.remove_button);
            holder.lineNumber = (TextView) convertView.findViewById(R.id.line_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Instruction i = instructionList.get(position);
        if ((position == 0) && (instructionList.size() == 1)) {
            holder.removeButton.setVisibility(View.INVISIBLE);
        } else {
            holder.removeButton.setVisibility(View.VISIBLE);
        }

        holder.editButton.setTag(position);
        holder.addButton.setTag(position);
        holder.removeButton.setTag(position);
        holder.commandList.setTag(position);

        Integer cmdpos = ((ArrayAdapter<String>) holder.commandList.getAdapter()).getPosition(i.getName());
        holder.commandList.setSelection(cmdpos);

        holder.instructionText.setText(i.getInstruction());
        holder.commandList.setTag(position);
        convertView.setOnClickListener(onViewClick());
        holder.commandList.setOnItemSelectedListener(onCommandSelected());
        holder.editButton.setOnClickListener(onEditClick());
        holder.addButton.setOnClickListener(onAddClick());
        holder.removeButton.setOnClickListener(onRemoveClick());

        holder.lineNumber.setText(((Integer) position).toString());
        return convertView;
    }


    /**
     * @return click listener for when an item is clicked
     */
    private View.OnClickListener onViewClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int[] position = new int[2];
                // v.getLocationOnScreen(position);
                // Toast.makeText(((View) v.getParent()).getContext(), ((Integer) position[1]).toString(), Toast.LENGTH_SHORT).show();

            }
        };
    }

    /**
     * @return command list listner for when a command is connected
     */
    private AdapterView.OnItemSelectedListener onCommandSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Integer position = (Integer) parent.getTag();
                if (position < 0) {
                    return;
                }
                Spinner spinner = (Spinner) parent;
                Instruction i = instructionList.get(position);
                String instructionText = spinner.getSelectedItem().toString();
                if (!i.getName().equals(instructionText)) {
                    try {
                        i = InstructionMaker.generateInstruction(instructionText);
                        instructionList.set(position, i);
                        openInstructionDialog(i);
                    } catch (Exception e) {
                        Toast.makeText(((View) parent.getParent()).getContext(), "Not a valid instruction", Toast.LENGTH_SHORT).show();
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }


    /**
     * @return listener for when the edit button is clicked
     */
    private View.OnClickListener onEditClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer) v.getTag();
                Instruction inst = instructionList.get(position);
                openInstructionDialog(inst);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @param inst dialogue to edit an instruction
     */
    private void openInstructionDialog(Instruction inst) {
        final InstructionAdapter adapter = this;
        InstructionDialog newFragment = new InstructionDialog();
        newFragment.setInstruction(inst);
        newFragment.setAdapter(adapter);
        newFragment.show(((FragmentActivity) context).getFragmentManager(), context.getString(R.string.instructiondialog_title));
    }

    /**
     * @return listener for add instruction button
     */
    private View.OnClickListener onAddClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer) v.getTag();
                Instruction newInstruction = new RemInstruction();
                instructionList.add(position + 1, newInstruction);
                updateGotoLinesAdd(position);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @return listener for remove instruction button
     */
    private View.OnClickListener onRemoveClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                instructionList.remove(position);
                updateGotoLinesDelete(position);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @param fromPosition update all of the goto lines when a line is deleted
     */
    private void updateGotoLinesDelete(int fromPosition) {
        Instruction inst;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = instructionList.get(i);
            GotoInstruction gi;
            if (inst instanceof GotoInstruction) {
                gi = (GotoInstruction) inst;
                if (gi.getGotoLine() != null && gi.getGotoLine() < fromPosition) {
                    gi.decreaseGotoLine();
                }
            }
            if (inst instanceof IfInstruction) {
                IfInstruction ifi = (IfInstruction) inst;
                gi = ifi.getGotoInstruction();
                if (gi.getGotoLine() != null && gi.getGotoLine() > fromPosition) {
                    gi.decreaseGotoLine();
                    ifi.updateInstructionText();
                }
            }
        }
    }

    /**
     * @param toPosition update all of the goto lines when a line is added
     */
    private void updateGotoLinesAdd(int toPosition) {
        Instruction inst;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = instructionList.get(i);
            GotoInstruction gi;
            if (inst instanceof GotoInstruction) {
                gi = (GotoInstruction) inst;
                if (gi.getGotoLine() != null && gi.getGotoLine() > toPosition) {
                    ((GotoInstruction) inst).increaseGotoLine();
                }
            }
            if (inst instanceof IfInstruction) {
                IfInstruction ifi = (IfInstruction) inst;
                gi = ifi.getGotoInstruction();
                if (gi.getGotoLine() != null && gi.getGotoLine() > toPosition) {
                    gi.increaseGotoLine();
                    ifi.updateInstructionText();
                }
            }
        }
    }

    /**
     * static class for caching all of the instruction line views
     */
    private static class ViewHolder {

        public TextView lineNumber;
        public TextView instructionText;
        public Spinner commandList;
        public Button editButton;
        public Button addButton;
        public Button removeButton;
    }
}
